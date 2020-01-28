package com.ilya4.beerplease.presentation.di.module

import com.ilya4.beerplease.presentation.di.module.activity.AppActivityModule
import com.ilya4.beerplease.presentation.di.module.activity.FindProfileBeerByBarcodeModule
import com.ilya4.beerplease.presentation.di.module.activity.MainModule
import com.ilya4.beerplease.presentation.di.module.fragment.*
import com.ilya4.beerplease.presentation.view.activity.AppActivity
import com.ilya4.beerplease.presentation.view.activity.FindProfileBeerByBarcodeFragment
import com.ilya4.beerplease.presentation.view.activity.MainFragment
import com.ilya4.beerplease.presentation.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    //activity
    @ContributesAndroidInjector(modules = [AppActivityModule::class])
    abstract fun bindAppActivity(): AppActivity
    @ContributesAndroidInjector(modules = [FindProfileBeerByBarcodeModule::class])
    abstract fun bindFindProfileBeerByBarcodeActivity(): FindProfileBeerByBarcodeFragment

    //fragment
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainFlowFragment(): MainFragment
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindSearchFragment(): SearchTabFragment
    @ContributesAndroidInjector(modules = [UserProfileModule::class])
    abstract fun bindUserProfileFragment(): UserProfileTabFragment
    @ContributesAndroidInjector(modules = [ScanBarcodeModule::class])
    abstract fun bindScanBarcodeFragment() : ScanBarcodeTabFragment
    @ContributesAndroidInjector(modules = [ChooseBeerStyleModule::class])
    abstract fun bindChooseBeerStyleFragment(): ChooseBeerStyleTabFragment
    @ContributesAndroidInjector(modules = [ChooseBreweryModule::class])
    abstract fun bindChooseBreweryFragment(): ChooseBreweryTabFragment
    @ContributesAndroidInjector(modules = [BeerCardModule::class])
    abstract fun bindBeerCardFragment(): BeerCardTabFragment
    @ContributesAndroidInjector(modules = [AddNewBeerModule::class])
    abstract fun bindAddNewBeerFragment(): AddNewBeerTabFragment

}
