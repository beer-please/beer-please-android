package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FChooseBreweryMvpView
import io.reactivex.processors.BehaviorProcessor

class FChooseBreweryPresenter(view: FChooseBreweryMvpView,
                              behaviorProcessor: BehaviorProcessor<Boolean>)
    : BasePresenter<FChooseBreweryMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        return false
    }

    override fun bindEvents(activity: BaseActivity) {

    }
}