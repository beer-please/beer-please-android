package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.base.BaseMvpPresenter
import com.ilya4.beerplease.presentation.view.view.FindBeerByBarcodeMvpView
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class FlowFindBeerByBarcodePresenter(router: Router) : BaseMvpPresenter<FindBeerByBarcodeMvpView>(router) {


    fun backToMain() {
        router.exit()
    }
}