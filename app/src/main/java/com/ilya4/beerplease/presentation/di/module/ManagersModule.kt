package com.ilya4.beerplease.presentation.di.module


import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetectorOptions
import com.google.gson.Gson
import com.ilya4.beerplease.data.io.model.RestApi
import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.presentation.app.RestManager

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ManagersModule {

    @Provides
    @Singleton
    fun provideRestManager(restApi: RestApi, gson: Gson, settingsDataSource: SettingsDataSource) : RestManager {
        return RestManager(restApi, gson, settingsDataSource)
    }

    @Provides
    @Singleton
    fun provideFirebaseVisionDetector(): FirebaseVisionBarcodeDetector {
        val options = FirebaseVisionBarcodeDetectorOptions.Builder()
            .setBarcodeFormats(
                FirebaseVisionBarcode.FORMAT_EAN_8,
                FirebaseVisionBarcode.FORMAT_EAN_13,
                FirebaseVisionBarcode.FORMAT_CODE_128,
                FirebaseVisionBarcode.FORMAT_CODE_39,
                FirebaseVisionBarcode.FORMAT_CODE_93,
                FirebaseVisionBarcode.FORMAT_UPC_A,
                FirebaseVisionBarcode.FORMAT_UPC_E)
            .build()

        return FirebaseVision.getInstance().getVisionBarcodeDetector(options)
    }
}