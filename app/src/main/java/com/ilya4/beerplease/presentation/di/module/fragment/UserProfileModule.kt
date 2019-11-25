package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.presentation.presenter.FUserProfilePresenter
import com.ilya4.beerplease.presentation.view.view.FUserProfileMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor


@Module
class UserProfileModule {

    @Provides
    fun provideFUserProfilePresenter(): FUserProfilePresenter = FUserProfilePresenter()
}