package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.domain.usecase.GetBeerByIdUseCase
import com.ilya4.beerplease.presentation.presenter.FBeerCardPresenter
import dagger.Module
import dagger.Provides

@Module
class BeerCardModule {
    @Provides
    fun provideFBeerCardPresenter(getBeerByIdUseCase: GetBeerByIdUseCase): FBeerCardPresenter {
        return FBeerCardPresenter(getBeerByIdUseCase)
    }
}