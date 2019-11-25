package com.ilya4.beerplease.presentation.view.view

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FScanBarcodeMvpView: MvpView {
    fun initCamera()
    fun startCamera(preview: Preview, imageAnalysis: ImageAnalysis)
    fun showBarcodeToast(barcode: String)
}