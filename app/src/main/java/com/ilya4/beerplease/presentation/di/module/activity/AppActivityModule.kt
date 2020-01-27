package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.presentation.presenter.AppPresenter
import dagger.Module
import dagger.Provides

@Module
class AppActivityModule {

    @Provides
    fun provideAppPresenter(): AppPresenter = AppPresenter()
}