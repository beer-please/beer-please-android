package com.ilya4.beerplease.presentation.view.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface AFindProfileBeerByBarcodeMvpView: MvpView