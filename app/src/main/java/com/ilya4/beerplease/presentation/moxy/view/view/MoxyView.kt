package com.ilya4.beerplease.presentation.moxy.view.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface MoxyView: MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateText(msg: String)
}