package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.domain.usecase.DefaultObserver
import com.ilya4.beerplease.domain.usecase.GetBeerByIdUseCase
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FBeerCardMvpView
import io.reactivex.processors.BehaviorProcessor

class FBeerCardPresenter(view: FBeerCardMvpView,
                         behaviorProcessor: BehaviorProcessor<Boolean>,
                         private val getBeerByIdUseCase: GetBeerByIdUseCase): BasePresenter<FBeerCardMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        getBeerById(-1)
        return false
    }

    override fun bindEvents(activity: BaseActivity) {
    }

    private fun getBeerById(id: Int) {
        getBeerByIdUseCase.execute(GetBeerByIdObserver(), GetBeerByIdUseCase.Params.forGetBeerById(id))
    }

    inner class GetBeerByIdObserver: DefaultObserver<GetBeerByIdUseCase.Result>() {
        override fun onNext(result: GetBeerByIdUseCase.Result) {

        }
    }
}