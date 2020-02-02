package com.ilya4.beerplease.presentation.di.module.fragment

import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.ilya4.beerplease.presentation.presenter.ScanBarcodePresenter
import dagger.Module
import dagger.Provides

@Module
class ScanBarcodeModule {

    @Provides
    fun provideFScanBarcodePresenter(detector: FirebaseVisionBarcodeDetector) = ScanBarcodePresenter(detector)

}