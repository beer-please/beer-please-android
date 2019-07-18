package com.ilya4.beerplease.presentation.presenter


import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.MvpView
import com.michaelflisar.rxbus2.interfaces.IRxBusQueue
import com.michaelflisar.rxbus2.rx.RxDisposableManager
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher

abstract class BasePresenter<V : MvpView> (val view : V, val behaviorProcessor: BehaviorProcessor<Boolean>) :
    IRxBusQueue {
    protected val activity : BaseActivity
    init {

        if (view is BaseFragment)
            activity = (view as BaseFragment).activity as BaseActivity
        else
            activity = view as BaseActivity
    }
    override fun isBusResumed(): Boolean = behaviorProcessor.value ?: false

    override fun getResumeObservable(): Publisher<Boolean> = behaviorProcessor

    public abstract fun init() : Boolean

    protected abstract fun bindEvents(activity: BaseActivity)

    private fun unbindEvents() {
        RxDisposableManager.unsubscribe(activity)
    }

    private fun rebindEvents() {
        unbindEvents()
        bindEvents(activity)
    }

    fun start() {
        resume()
        rebindEvents()
    }

    fun stop() {
        pause()
        unbindEvents()
    }

    fun resume() {
        behaviorProcessor.onNext(true)
    }

    fun pause() {
        behaviorProcessor.onNext(false)
        unbindEvents()
    }


}