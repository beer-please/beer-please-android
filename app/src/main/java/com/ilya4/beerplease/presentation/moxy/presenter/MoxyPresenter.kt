package com.ilya4.beerplease.presentation.moxy.presenter

import com.ilya4.beerplease.presentation.moxy.view.view.MoxyView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class MoxyPresenter: MvpPresenter<MoxyView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.updateText("Привет")
    }
}