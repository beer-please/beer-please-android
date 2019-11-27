package com.ilya4.beerplease.presentation.di.module

import com.ilya4.beerplease.presentation.di.module.activity.FindProfileBeerByBarcodeModule
import com.ilya4.beerplease.presentation.di.module.activity.MainModule
import com.ilya4.beerplease.presentation.di.module.fragment.*
import com.ilya4.beerplease.presentation.view.activity.FindProfileBeerByBarcodeActivity
import com.ilya4.beerplease.presentation.view.activity.MainActivity
import com.ilya4.beerplease.presentation.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    //activity
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [FindProfileBeerByBarcodeModule::class])
    abstract fun bindFindProfileBeerByBarcodeActivity(): FindProfileBeerByBarcodeActivity

    //fragment
    @ContributesAndroidInjector(modules = [SearchModule::class])
    abstract fun bindSearchFragment(): SearchFragment
    @ContributesAndroidInjector(modules = [UserProfileModule::class])
    abstract fun bindUserProfileFragment(): UserProfileFragment
    @ContributesAndroidInjector(modules = [ScanBarcodeModule::class])
    abstract fun bindScanBarcodeFragment() : ScanBarcodeFragment
    @ContributesAndroidInjector(modules = [ChooseBeerStyleModule::class])
    abstract fun bindChooseBeerStyleFragment(): ChooseBeerStyleFragment
    @ContributesAndroidInjector(modules = [ChooseBreweryModule::class])
    abstract fun bindChooseBreweryFragment(): ChooseBreweryFragment
    @ContributesAndroidInjector(modules = [BeerCardModule::class])
    abstract fun bindBeerCardFragment(): BeerCardFragment
    @ContributesAndroidInjector(modules = [AddNewBeerModule::class])
    abstract fun bindAddNewBeerFragment(): AddNewBeerFragment

}
