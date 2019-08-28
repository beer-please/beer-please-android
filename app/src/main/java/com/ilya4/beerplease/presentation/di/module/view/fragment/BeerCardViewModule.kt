package com.ilya4.beerplease.presentation.di.module.view.fragment

import com.ilya4.beerplease.presentation.view.fragment.BeerCardFragment
import com.ilya4.beerplease.presentation.view.view.FBeerCardMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class BeerCardViewModule {

    @Binds
    abstract fun bindFBeerCardMvpView(fragment: BeerCardFragment): FBeerCardMvpView
}