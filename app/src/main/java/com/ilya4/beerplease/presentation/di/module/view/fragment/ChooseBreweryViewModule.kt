package com.ilya4.beerplease.presentation.di.module.view.fragment

import com.ilya4.beerplease.presentation.view.fragment.ChooseBreweryFragment
import com.ilya4.beerplease.presentation.view.view.FChooseBreweryMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class ChooseBreweryViewModule {

    @Binds
    abstract fun bindFChooseBreweryMvpView(fragment: ChooseBreweryFragment): FChooseBreweryMvpView
}