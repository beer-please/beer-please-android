package com.ilya4.beerplease.domain.usecase

import com.ilya4.beerplease.domain.executor.PostExecutionThread
import com.ilya4.beerplease.domain.executor.ThreadExecutor
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class UseCase<T, Params> (private val threadExecutor : ThreadExecutor,
                                   private val postExecutionThread : PostExecutionThread
) {
    private var disposables : CompositeDisposable

    init {
        disposables = CompositeDisposable()
    }

    protected abstract fun buildUseCaseObservable(params: Params) : Observable<T>

    fun execute(observer : DisposableObserver<T>, params: Params) {
        if (isDisposed())
            reload()
        val observable = this.buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheluder())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        if (!disposables.isDisposed)
            disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        if (disposable != null && disposables != null)
            disposables.add(disposable)
        else
            throw NullPointerException()
    }

    fun isDisposed() : Boolean = disposables.isDisposed

    fun reload() {
        disposables = CompositeDisposable()
    }

}