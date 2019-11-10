package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.domain.usecase.GetBeerByIdUseCase
import com.ilya4.beerplease.presentation.moxy.presenter.MoxyPresenter
import dagger.Module
import dagger.Provides

@Module
class MoxyModule {

    @Provides
    fun provideMoxyPresenter(getBeerByIdUseCase: GetBeerByIdUseCase): MoxyPresenter {
        return MoxyPresenter(getBeerByIdUseCase)
    }
}