package com.ilya4.beerplease.presentation.di.module.view.activity

import com.ilya4.beerplease.presentation.view.activity.base.MainActivity
import com.ilya4.beerplease.presentation.view.view.AMainMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class MainViewModule {

    @Binds
    abstract fun provideAMainMvpView(activity: MainActivity) : AMainMvpView
}