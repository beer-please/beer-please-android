package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.presentation.presenter.AppPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class AppActivityModule {

    @Provides
    fun provideAppPresenter(router: Router): AppPresenter = AppPresenter(router)
}