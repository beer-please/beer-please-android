package com.ilya4.beerplease.presentation.di.module.view.fragment

import com.ilya4.beerplease.presentation.view.fragment.UserProfileFragment
import com.ilya4.beerplease.presentation.view.view.FUserProfileMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class UserProfileViewModule {

    @Binds
    abstract fun bindFUserProfileMvpView(fragment: UserProfileFragment): FUserProfileMvpView
}