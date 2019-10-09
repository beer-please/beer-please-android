package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.presentation.presenter.FChooseBreweryPresenter
import com.ilya4.beerplease.presentation.view.view.FChooseBreweryMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor

@Module
class ChooseBreweryModule {

    @Provides
    fun provideFChooseBreweryPresenter(view: FChooseBreweryMvpView,
                                       behaviorProcessor: BehaviorProcessor<Boolean>): FChooseBreweryPresenter {
        return FChooseBreweryPresenter(view, behaviorProcessor)
    }
}