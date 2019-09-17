package com.ilya4.beerplease.presentation.di.module.view.fragment

import com.ilya4.beerplease.presentation.view.fragment.AddNewBeerFragment
import com.ilya4.beerplease.presentation.view.view.FAddNewBeerMvpView
import com.ilya4.beerplease.presentation.view.view.FBeerCardMvpView

import dagger.Binds
import dagger.Module

@Module
abstract class AddNewBeerViewModule {

    @Binds
    abstract fun bindFAddBeerCardMvpView(fragment: AddNewBeerFragment): FAddNewBeerMvpView
}