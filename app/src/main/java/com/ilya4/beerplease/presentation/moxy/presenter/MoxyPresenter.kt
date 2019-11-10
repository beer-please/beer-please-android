package com.ilya4.beerplease.presentation.moxy.presenter

import com.ilya4.beerplease.domain.usecase.GetBeerByIdUseCase
import com.ilya4.beerplease.presentation.moxy.view.view.MoxyView
import moxy.InjectViewState
import moxy.MvpPresenter
import timber.log.Timber

@InjectViewState
class MoxyPresenter
    (private val getBeerByIdUseCase: GetBeerByIdUseCase): MvpPresenter<MoxyView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Timber.d("onFirstAttach")

        viewState.updateText("Привет")
    }
}