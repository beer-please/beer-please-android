package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.AFindProfileBeerByBarcodeMvpView
import io.reactivex.processors.BehaviorProcessor

class AFindProfileBeerByBarcodePresenter(view: AFindProfileBeerByBarcodeMvpView,
                                         behaviorProcessor: BehaviorProcessor<Boolean>)
    : BasePresenter<AFindProfileBeerByBarcodeMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        return false
    }

    override fun bindEvents(activity: BaseActivity) {}
}