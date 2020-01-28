package com.ilya4.beerplease.presentation.view.activity

import android.os.Bundle
import android.view.View
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AFindProfileBeerByBarcodePresenter
import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeTabFragment
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.AFindProfileBeerByBarcodeMvpView
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FindProfileBeerByBarcodeFragment: BaseFragment<AFindProfileBeerByBarcodePresenter>(R.layout.activity_find_profile_beer_by_barcode), AFindProfileBeerByBarcodeMvpView {

    @InjectPresenter
    lateinit var presenter: AFindProfileBeerByBarcodePresenter
    @ProvidePresenter
    override fun providePresenter(): AFindProfileBeerByBarcodePresenter {
        return super.providePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = ScanBarcodeTabFragment().newInstance(Bundle(), true)
        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}