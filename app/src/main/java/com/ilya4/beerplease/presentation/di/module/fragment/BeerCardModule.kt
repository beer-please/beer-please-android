package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.presentation.presenter.FBeerCardPresenter
import com.ilya4.beerplease.presentation.view.view.FBeerCardMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor

@Module
class BeerCardModule {

    @Provides
    fun provideFBeerCardPresenter(view: FBeerCardMvpView,
                                  behaviorProcessor: BehaviorProcessor<Boolean>): FBeerCardPresenter {
        return FBeerCardPresenter(view, behaviorProcessor)
    }
}