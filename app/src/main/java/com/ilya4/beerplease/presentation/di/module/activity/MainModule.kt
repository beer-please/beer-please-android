package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.presentation.presenter.FlowMainPresenter
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Router

@Module
class MainModule {

    @Provides
    fun provideAMainPresenter(router: Router): FlowMainPresenter = FlowMainPresenter(router)
}