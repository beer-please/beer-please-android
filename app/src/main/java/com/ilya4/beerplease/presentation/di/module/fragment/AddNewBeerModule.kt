package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.presentation.presenter.FAddNewBeerPresenter
import com.ilya4.beerplease.presentation.view.view.FAddNewBeerMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor

@Module
class AddNewBeerModule {

    @Provides
    fun provideFAddNewBeerPresenter(view: FAddNewBeerMvpView,
                                    behaviorProcessor: BehaviorProcessor<Boolean>): FAddNewBeerPresenter {
        return FAddNewBeerPresenter(view, behaviorProcessor)
    }
}