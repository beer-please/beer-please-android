package com.ilya4.beerplease.presentation.presenter

import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.view.view.FChooseBreweryMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class FChooseBreweryPresenter(val tempRepository: AddBeerTempRepository) : MvpPresenter<FChooseBreweryMvpView>()