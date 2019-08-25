package com.ilya4.beerplease.presentation.di.module

import com.ilya4.beerplease.presentation.di.module.activity.FindProfileBeerByBarcodeModule
import com.ilya4.beerplease.presentation.di.module.activity.MainModule
import com.ilya4.beerplease.presentation.di.module.fragment.ScanBarcodeModule
import com.ilya4.beerplease.presentation.di.module.fragment.SearchModule
import com.ilya4.beerplease.presentation.di.module.fragment.UserProfileModule
import com.ilya4.beerplease.presentation.di.module.view.activity.FindProfileBeerByBarcodeViewModule
import com.ilya4.beerplease.presentation.di.module.view.activity.MainViewModule
import com.ilya4.beerplease.presentation.di.module.view.fragment.ScanBarcodeViewModule
import com.ilya4.beerplease.presentation.di.module.view.fragment.SearchViewModule
import com.ilya4.beerplease.presentation.di.module.view.fragment.UserProfileViewModule
import com.ilya4.beerplease.presentation.view.activity.FindProfileBeerByBarcodeActivity
import com.ilya4.beerplease.presentation.view.activity.MainActivity
import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeFragment
import com.ilya4.beerplease.presentation.view.fragment.SearchFragment
import com.ilya4.beerplease.presentation.view.fragment.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    //activity
    @ContributesAndroidInjector(modules = [MainViewModule::class, MainModule::class])
    abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [FindProfileBeerByBarcodeViewModule::class, FindProfileBeerByBarcodeModule::class])
    abstract fun bindFindProfileBeerByBarcodeActivity(): FindProfileBeerByBarcodeActivity

    //fragment
    @ContributesAndroidInjector(modules = [ScanBarcodeViewModule::class, ScanBarcodeModule::class])
    abstract fun bindScanBarcodeFragment() : ScanBarcodeFragment
    @ContributesAndroidInjector(modules = [SearchViewModule::class, SearchModule::class])
    abstract fun bindSearchFragment(): SearchFragment
    @ContributesAndroidInjector(modules = [UserProfileViewModule::class, UserProfileModule::class])
    abstract fun bindUserProfileFragment(): UserProfileFragment
}
