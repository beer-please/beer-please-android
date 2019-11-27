package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.presentation.presenter.AFindProfileBeerByBarcodePresenter
import dagger.Module
import dagger.Provides

@Module
class FindProfileBeerByBarcodeModule {

    @Provides
    fun provideAFindProfileBeerByBarcodePresenter(): AFindProfileBeerByBarcodePresenter = AFindProfileBeerByBarcodePresenter()
}