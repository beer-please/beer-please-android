package com.ilya4.beerplease.presentation.presenter.base

import moxy.MvpPresenter
import moxy.MvpView

abstract class BaseMvpPresenter<V: MvpView>: MvpPresenter<V>() {

}