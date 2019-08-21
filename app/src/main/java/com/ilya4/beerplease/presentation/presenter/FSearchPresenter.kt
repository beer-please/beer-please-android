package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FSearchMvpView
import io.reactivex.processors.BehaviorProcessor

class FSearchPresenter(view: FSearchMvpView,
                       behaviorProcessor: BehaviorProcessor<Boolean>):
    BasePresenter<FSearchMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        return true
    }

    override fun bindEvents(activity: BaseActivity) {
    }
}