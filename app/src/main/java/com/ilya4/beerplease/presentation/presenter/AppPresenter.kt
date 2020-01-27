package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.view.AppMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AppPresenter: MvpPresenter<AppMvpView>()