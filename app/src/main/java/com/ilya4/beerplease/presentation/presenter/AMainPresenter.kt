package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.presentation.view.view.AMainMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AMainPresenter: MvpPresenter<AMainMvpView>()