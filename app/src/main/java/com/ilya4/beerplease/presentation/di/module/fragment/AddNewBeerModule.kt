package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.presentation.presenter.FAddNewBeerPresenter
import dagger.Module
import dagger.Provides

@Module
class AddNewBeerModule {

    @Provides
    fun provideFAddNewBeerPresenter(): FAddNewBeerPresenter {
        return FAddNewBeerPresenter()
    }
}