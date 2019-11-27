package com.ilya4.beerplease.presentation.view.activity

import android.os.Bundle
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AFindProfileBeerByBarcodePresenter
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeFragment
import com.ilya4.beerplease.presentation.view.view.AFindProfileBeerByBarcodeMvpView
import dagger.android.AndroidInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class FindProfileBeerByBarcodeActivity: BaseActivity<AFindProfileBeerByBarcodePresenter>(R.layout.activity_find_profile_beer_by_barcode), AFindProfileBeerByBarcodeMvpView {

    @InjectPresenter
    lateinit var presenter: AFindProfileBeerByBarcodePresenter
    @ProvidePresenter
    override fun providePresenter(): AFindProfileBeerByBarcodePresenter {
        return super.providePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_profile_beer_by_barcode)
        val fragment = ScanBarcodeFragment().newInstance(Bundle(), true)
        showFragment(ScanBarcodeFragment.TAG, fragment, false)
    }

    //TODO убрать эту активити и перевести все в SingleActivity
}