package com.ilya4.beerplease.presentation.di.module.fragment

import com.ilya4.beerplease.presentation.presenter.UserProfilePresenter
import dagger.Module
import dagger.Provides


@Module
class UserProfileModule {

    @Provides
    fun provideFUserProfilePresenter(): UserProfilePresenter = UserProfilePresenter()
}