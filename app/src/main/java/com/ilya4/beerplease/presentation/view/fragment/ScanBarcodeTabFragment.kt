package com.ilya4.beerplease.presentation.view.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Matrix
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraX
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.lifecycle.LifecycleOwner
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.app.Constants.REQUEST_CAMERA
import com.ilya4.beerplease.presentation.presenter.FScanBarcodePresenter
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.fragment.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.view.FScanBarcodeMvpView
import com.ilya4.beerplease.utils.PermissionHelper
import com.ilya4.beerplease.utils.PermissionHelper.mayRequestCamera
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_scan_barcode.*
import moxy.MvpPresenter
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ScanBarcodeTabFragment: BaseTabFragment<FScanBarcodePresenter>(R.layout.fragment_scan_barcode), FScanBarcodeMvpView, LifecycleOwner {

    @InjectPresenter
    lateinit var presenter: FScanBarcodePresenter
    @ProvidePresenter
    override fun providePresenter(): FScanBarcodePresenter {
        return super.providePresenter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan_barcode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonListeners()
    }

    override fun onStart() {
        super.onStart()
        requestCameraPermission()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val permissionGranted = PermissionHelper.checkGranted(grantResults)
        if (requestCode == REQUEST_CAMERA && permissionGranted)
            requestCameraPermission()
        else
            showPermissionDeniedDialog()
    }

    override fun initCamera() {
        cameraView.post { presenter.requestStartCamera() }
    }

    override fun startCamera(preview: Preview, imageAnalysis: ImageAnalysis) {
        preview.setOnPreviewOutputUpdateListener {

            val parent = cameraView.parent as ViewGroup
            parent.removeView(cameraView)
            parent.addView(cameraView, 0)

            cameraView.surfaceTexture = it.surfaceTexture

            updateTransform()
        }

        CameraX.bindToLifecycle(this, imageAnalysis, preview)
        torchCheckBox.isEnabled = true
    }

    override fun showBarcodeToast(barcode: String) {
        Toast.makeText(context,
                    "Barcode: $barcode",
                    Toast.LENGTH_SHORT).show()
    }

    private fun updateTransform() {
        val matrix = Matrix()

        val centerX = cameraView.width / 2f
        val centerY = cameraView.height / 2f

        val rotationDegrees = when(cameraView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }
        matrix.postRotate(-rotationDegrees.toFloat(), centerX, centerY)

        cameraView.setTransform(matrix)
    }

    private fun initButtonListeners() {
        torchCheckBox.setOnCheckedChangeListener { _, isChecked ->
            presenter.torchOn(isChecked)
        }
    }

    private fun requestCameraPermission() {
        if (mayRequestCamera(requireActivity() as BaseActivity<out MvpPresenter<*>>, this, null))
            initCamera()
    }

    private fun showPermissionDeniedDialog() {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(R.string.pd_camera_dialog_title)
                .setCancelable(false)
                .setMessage(R.string.pd_camera_dialog_description)
                .setPositiveButton(R.string.pd_camera_dialog_settings) { _, _ ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", context?.packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
        val dialog = builder.create()
            dialog.show()
    }

    companion object{
        const val TAG = "ScanBarcodeTabFragment"
    }
}