package com.ilya4.beerplease.presentation.di.module.view.fragment

import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeFragment
import com.ilya4.beerplease.presentation.view.view.FScanBarcodeMvpView
import dagger.Binds
import dagger.Module

@Module
abstract class ScanBarcodeViewModule {

    @Binds
    abstract fun bindFScanBarcodeViewModule(fragment: ScanBarcodeFragment): FScanBarcodeMvpView
}