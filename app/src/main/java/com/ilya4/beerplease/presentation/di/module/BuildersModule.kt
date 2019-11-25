package com.ilya4.beerplease.presentation.di.module

import com.ilya4.beerplease.presentation.di.module.activity.FindProfileBeerByBarcodeModule
import com.ilya4.beerplease.presentation.di.module.activity.MainModule
import com.ilya4.beerplease.presentation.di.module.activity.MoxyModule
import com.ilya4.beerplease.presentation.di.module.fragment.*
import com.ilya4.beerplease.presentation.di.module.view.activity.FindProfileBeerByBarcodeViewModule
import com.ilya4.beerplease.presentation.di.module.view.activity.MainViewModule
import com.ilya4.beerplease.presentation.di.module.view.fragment.*
import com.ilya4.beerplease.presentation.moxy.view.activity.MoxyActivity
import com.ilya4.beerplease.presentation.view.activity.FindProfileBeerByBarcodeActivity
import com.ilya4.beerplease.presentation.view.activity.MainActivity
import com.ilya4.beerplease.presentation.view.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    //activity
    @ContributesAndroidInjector(modules = [MainViewModule::class, MainModule::class])
    abstract fun bindMainActivity(): MainActivity
    @ContributesAndroidInjector(modules = [FindProfileBeerByBarcodeViewModule::class, FindProfileBeerByBarcodeModule::class])
    abstract fun bindFindProfileBeerByBarcodeActivity(): FindProfileBeerByBarcodeActivity

    //moxy activity
    @ContributesAndroidInjector(modules = [MoxyModule::class])
    abstract fun bindMoxyActivity(): MoxyActivity

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
