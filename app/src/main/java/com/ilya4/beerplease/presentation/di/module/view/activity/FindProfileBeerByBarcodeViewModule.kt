package com.ilya4.beerplease.presentation.di.module.view.activity

import com.ilya4.beerplease.presentation.view.activity.FindProfileBeerByBarcodeActivity
import com.ilya4.beerplease.presentation.view.view.AFindProfileBeerByBarcodeMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class FindProfileBeerByBarcodeViewModule {

    @Binds
    abstract fun provideAFindProfileBeerByBarcodeMvpView(activity: FindProfileBeerByBarcodeActivity): AFindProfileBeerByBarcodeMvpView
}