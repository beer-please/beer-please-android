package com.ilya4.beerplease.presentation.base

import moxy.MvpPresenter
import moxy.MvpView
import ru.terrakok.cicerone.Router

abstract class BaseMvpPresenter<V: MvpView>(protected val router: Router): MvpPresenter<V>() {
}