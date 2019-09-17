package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FAddNewBeerMvpView
import io.reactivex.processors.BehaviorProcessor

class FAddNewBeerPresenter(view: FAddNewBeerMvpView,
                           behaviorProcessor: BehaviorProcessor<Boolean>): BasePresenter<FAddNewBeerMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        return false
    }

    override fun bindEvents(activity: BaseActivity) {

    }
}