package com.ilya4.beerplease.presentation.view.activity

import android.os.Bundle
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AFindProfileBeerByBarcodePresenter
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeFragment
import com.ilya4.beerplease.presentation.view.view.AFindProfileBeerByBarcodeMvpView
import dagger.android.AndroidInjection
import javax.inject.Inject

class FindProfileBeerByBarcodeActivity: BaseActivity(),AFindProfileBeerByBarcodeMvpView {

    @Inject
    lateinit var presenter: AFindProfileBeerByBarcodePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_profile_beer_by_barcode)
        presenter.init()
        val fragment = ScanBarcodeFragment().newInstance(Bundle())
        showFragment(ScanBarcodeFragment.TAG, fragment, false)
    }
}