package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FUserProfileMvpView
import io.reactivex.processors.BehaviorProcessor

class FUserProfilePresenter(
    view: FUserProfileMvpView,
    behaviorProcessor: BehaviorProcessor<Boolean>): BasePresenter<FUserProfileMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        return false
    }

    override fun bindEvents(activity: BaseActivity) {

    }
}