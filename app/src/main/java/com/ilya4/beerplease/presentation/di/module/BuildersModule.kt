package com.ilya4.beerplease.presentation.di.module

import com.ilya4.beerplease.presentation.di.module.activity.MainModule
import com.ilya4.beerplease.presentation.di.module.fragment.ScanBarcodeModule
import com.ilya4.beerplease.presentation.di.module.view.activity.MainViewModule
import com.ilya4.beerplease.presentation.di.module.view.fragment.ScanBarcodeViewModule
import com.ilya4.beerplease.presentation.view.activity.MainActivity
import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    //activity
    @ContributesAndroidInjector(modules = [MainViewModule::class, MainModule::class])
    abstract fun bindMainActivity() : MainActivity

    //fragment
    @ContributesAndroidInjector(modules = [ScanBarcodeViewModule::class, ScanBarcodeModule::class])
    abstract fun bindScanBarcodeFragment() : ScanBarcodeFragment
}
