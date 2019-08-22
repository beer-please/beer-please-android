package com.ilya4.beerplease.presentation.di.module.activity

import com.ilya4.beerplease.presentation.presenter.AFindProfileBeerByBarcodePresenter
import com.ilya4.beerplease.presentation.view.view.AFindProfileBeerByBarcodeMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor

@Module
class FindProfileBeerByBarcodeModule {

    @Provides
    fun provideAFindProfileBeerByBarcodePresenter(view: AFindProfileBeerByBarcodeMvpView,
                                                  behaviorProcessor: BehaviorProcessor<Boolean>): AFindProfileBeerByBarcodePresenter =
        AFindProfileBeerByBarcodePresenter(view, behaviorProcessor)
}