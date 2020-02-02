package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.base.BaseMvpPresenter
import com.ilya4.beerplease.presentation.view.view.AppMvpView
import moxy.InjectViewState
import ru.terrakok.cicerone.Router

@InjectViewState
class AppPresenter(router: Router): BaseMvpPresenter<AppMvpView>(router) {

}