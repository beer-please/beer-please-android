package com.ilya4.beerplease.presentation.di.module.view.fragment

import com.ilya4.beerplease.presentation.view.fragment.SearchFragment
import com.ilya4.beerplease.presentation.view.view.FSearchMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class SearchViewModule {

    @Binds
    abstract fun bindFSearchMvpView(fragment: SearchFragment): FSearchMvpView
}