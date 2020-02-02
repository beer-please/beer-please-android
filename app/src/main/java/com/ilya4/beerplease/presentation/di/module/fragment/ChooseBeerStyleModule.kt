package com.ilya4.beerplease.presentation.di.module.fragment

import com.google.gson.Gson
import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.presenter.ChooseBeerStylePresenter
import dagger.Module
import dagger.Provides

@Module
class ChooseBeerStyleModule {

    @Provides
    fun providesFChooseBeerStyleModule(gson: Gson, tempRepository: AddBeerTempRepository): ChooseBeerStylePresenter {
        return ChooseBeerStylePresenter(gson, tempRepository)
    }
}