package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.view.view.AddNewBeerMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class AddNewBeerPresenter(val tempRespository: AddBeerTempRepository): MvpPresenter<AddNewBeerMvpView>() {

    fun init(): Boolean {
        return false
    }

    fun getCacheData() {
        tempRespository.styleBeer?.let {
            viewState.setBeerStyle(it)
        }
    }
}