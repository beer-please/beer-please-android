package com.ilya4.beerplease.presentation.di.module.fragment

import com.google.gson.Gson
import com.ilya4.beerplease.presentation.presenter.FChooseBeerStylePresenter
import dagger.Module
import dagger.Provides

@Module
class ChooseBeerStyleModule {

    @Provides
    fun providesFChooseBeerStyleModule(gson: Gson): FChooseBeerStylePresenter {
        return FChooseBeerStylePresenter(gson)
    }
}