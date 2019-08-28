package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FBeerCardMvpView
import io.reactivex.processors.BehaviorProcessor

class FBeerCardPresenter(view: FBeerCardMvpView,
                         behaviorProcessor: BehaviorProcessor<Boolean>): BasePresenter<FBeerCardMvpView>(view, behaviorProcessor) {

    override fun init(): Boolean {
        return false
    }

    override fun bindEvents(activity: BaseActivity) {
    }
}