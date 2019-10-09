package com.ilya4.beerplease.presentation.di.module.view.fragment

import com.ilya4.beerplease.presentation.view.fragment.ChooseBeerStyleFragment
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class ChooseBeerStyleViewModule {

    @Binds
    abstract fun bindsFChooseBeerStyleMvpView(fragment: ChooseBeerStyleFragment): FChooseBeerStyleMvpView
}