package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.base.BaseMvpPresenter
import com.ilya4.beerplease.presentation.view.Screens
import com.ilya4.beerplease.presentation.view.view.MainMvpView
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class FlowMainPresenter(router: Router):  BaseMvpPresenter<MainMvpView>(router) {

    fun goToFindBeerByBarcode() {
        router.navigateTo(Screens.FindBeerByBarcodeFlow)
    }
}