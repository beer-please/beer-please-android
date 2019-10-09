package com.ilya4.beerplease.presentation.di.module.fragment

import com.google.gson.Gson
import com.ilya4.beerplease.presentation.presenter.FChooseBeerStylePresenter
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor

@Module
class ChooseBeerStyleModule {

    @Provides
    fun providesFChooseBeerStyleModule(view: FChooseBeerStyleMvpView,
                                       behaviorProcessor: BehaviorProcessor<Boolean>,
                                       gson: Gson): FChooseBeerStylePresenter {
        return FChooseBeerStylePresenter(view, behaviorProcessor, gson)
    }
}