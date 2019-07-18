package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.presentation.presenter.AMainPresenter
import com.ilya4.beerplease.presentation.view.view.AMainMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor

@Module
class MainModule {

    @Provides
    fun provideAMainPresenter(view: AMainMvpView,
                              behaviorProcessor: BehaviorProcessor<Boolean>): AMainPresenter =
        AMainPresenter(view, behaviorProcessor)
}