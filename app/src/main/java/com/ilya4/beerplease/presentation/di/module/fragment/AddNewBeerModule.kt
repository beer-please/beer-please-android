package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.presentation.presenter.AddNewBeerPresenter
import dagger.Module
import dagger.Provides

@Module
class AddNewBeerModule {

    @Provides
    fun provideFAddNewBeerPresenter(tempRepository: AddBeerTempRepository): AddNewBeerPresenter {
        return AddNewBeerPresenter(tempRepository)
    }
}