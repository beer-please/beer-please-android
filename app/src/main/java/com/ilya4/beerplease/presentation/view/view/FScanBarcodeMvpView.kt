package com.ilya4.beerplease.presentation.view.view

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview

interface FScanBarcodeMvpView: MvpView {
    fun initCamera()
    fun startCamera(preview: Preview, imageAnalysis: ImageAnalysis)
    fun showBarcodeToast(barcode: String)
}