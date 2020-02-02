package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.presenter.ChooseBreweryPresenter
import dagger.Module
import dagger.Provides

@Module
class ChooseBreweryModule {

    @Provides
    fun provideFChooseBreweryPresenter(tempRepository: AddBeerTempRepository): ChooseBreweryPresenter {
        return ChooseBreweryPresenter(tempRepository)
    }
}