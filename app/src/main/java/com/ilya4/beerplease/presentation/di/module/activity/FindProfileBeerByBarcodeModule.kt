package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.presentation.presenter.FlowFindBeerByBarcodePresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class FindProfileBeerByBarcodeModule {

    @Provides
    fun provideAFindProfileBeerByBarcodePresenter(router: Router): FlowFindBeerByBarcodePresenter = FlowFindBeerByBarcodePresenter(router)
}