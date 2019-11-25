package com.ilya4.beerplease.presentation.di.module.fragment

import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.ilya4.beerplease.presentation.presenter.FScanBarcodePresenter
import com.ilya4.beerplease.presentation.view.view.FScanBarcodeMvpView
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor

@Module
class ScanBarcodeModule {

    @Provides
    fun provideFScanBarcodePresenter(detector: FirebaseVisionBarcodeDetector) = FScanBarcodePresenter(detector)

}