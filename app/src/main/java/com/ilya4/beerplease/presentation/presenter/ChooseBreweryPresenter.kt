package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.view.view.ChooseBreweryMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class ChooseBreweryPresenter(val tempRepository: AddBeerTempRepository) : MvpPresenter<ChooseBreweryMvpView>()