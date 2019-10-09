package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import io.reactivex.processors.BehaviorProcessor

class FChooseBeerStylePresenter(view: FChooseBeerStyleMvpView,
                                behaviorProcessor: BehaviorProcessor<Boolean>)
    : BasePresenter<FChooseBeerStyleMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        return true
    }

    override fun bindEvents(activity: BaseActivity) {

    }
}