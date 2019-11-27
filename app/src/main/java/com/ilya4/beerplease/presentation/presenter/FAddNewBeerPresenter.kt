package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.view.FAddNewBeerMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FAddNewBeerPresenter: MvpPresenter<FAddNewBeerMvpView>() {

    fun init(): Boolean {
        return false
    }
}