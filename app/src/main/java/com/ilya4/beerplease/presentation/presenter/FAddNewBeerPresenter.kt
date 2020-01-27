package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.view.view.FAddNewBeerMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FAddNewBeerPresenter(val tempRespository: AddBeerTempRepository): MvpPresenter<FAddNewBeerMvpView>() {

    fun init(): Boolean {
        return false
    }

    fun getCacheData() {
        tempRespository.styleBeer?.let {
            viewState.setBeerStyle(it)
        }
    }
}