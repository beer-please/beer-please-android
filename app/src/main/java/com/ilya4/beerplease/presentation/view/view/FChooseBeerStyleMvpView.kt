package com.ilya4.beerplease.presentation.view.view

interface FChooseBeerStyleMvpView: MvpView {
    fun showSearchClearBtn(show: Boolean)
    fun updateStyleList(styleList: List<String>)
}