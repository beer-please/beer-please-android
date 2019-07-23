package com.ilya4.beerplease.presentation.presenter

import android.os.Handler
import android.os.HandlerThread
import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysisConfig
import androidx.camera.core.Preview
import androidx.camera.core.PreviewConfig
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.FScanBarcodeMvpView
import io.reactivex.processors.BehaviorProcessor
import com.ilya4.beerplease.utils.PermissionHelper.mayRequestCamera
import java.util.concurrent.TimeUnit

class FScanBarcodePresenter(view: FScanBarcodeMvpView,
                            behaviorProcessor: BehaviorProcessor<Boolean>,
                            private val detector: FirebaseVisionBarcodeDetector):
    BasePresenter<FScanBarcodeMvpView>(view, behaviorProcessor) {

    private val lastAnalyzedStamp = 0L

    override fun init(): Boolean {
        requestCameraPermission()
        return false
    }

    override fun bindEvents(activity: BaseActivity) {

    }

    fun requestCameraPermission() {
        if (mayRequestCamera(activity, fragment, null))
            view.initCamera()
    }

    fun requestStartCamera() {
        val previewConfig = PreviewConfig.Builder().apply {
            setTargetResolution(Size(720, 1280))
        }.build()

        val imageAnalysisConfig = ImageAnalysisConfig.Builder().apply {
            val analyzerThread = HandlerThread("OCR").apply { start() }
            setCallbackHandler(Handler(analyzerThread.looper))
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
            setTargetResolution(Size(720, 1280))
        }.build()


        val imageAnalysis = ImageAnalysis(imageAnalysisConfig)
        imageAnalysis.setAnalyzer { imageProxy, rotationDegrees ->
            if (imageProxy?.image == null || imageProxy.image == null) return@setAnalyzer

            val timestamp = System.currentTimeMillis()
            if (timestamp - lastAnalyzedStamp >= TimeUnit.SECONDS.toMillis(1)) {
                val visionImage = FirebaseVisionImage.fromMediaImage(imageProxy.image!!,
                    getOrientationFromRotation(rotationDegrees))

                runBarcodeScanner(visionImage)
            }
        }
        val preview = Preview(previewConfig)

        view.startCamera(preview, imageAnalysis)
    }

    private fun runBarcodeScanner(image: FirebaseVisionImage) {
        detector.detectInImage(image).addOnSuccessListener { barcodeList ->
            barcodeList.forEach { _->
                barcodeList[0].rawValue?.let { view.showBarcodeToast(it) }
            }
        }
    }

    private fun getOrientationFromRotation(rotationDegrees: Int): Int {
        return when (rotationDegrees) {
            0 -> FirebaseVisionImageMetadata.ROTATION_0
            90 -> FirebaseVisionImageMetadata.ROTATION_90
            180 -> FirebaseVisionImageMetadata.ROTATION_180
            270 -> FirebaseVisionImageMetadata.ROTATION_270
            else -> FirebaseVisionImageMetadata.ROTATION_90
        }
    }
}