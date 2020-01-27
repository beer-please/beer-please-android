package com.ilya4.beerplease.presentation.view.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FChooseBeerStyleMvpView: MvpView {
    fun showSearchClearBtn(show: Boolean)
    fun updateStyleList(styleList: List<String>)
    fun closeFragment()
}