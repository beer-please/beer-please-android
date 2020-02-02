package com.ilya4.beerplease.presentation.view.fragment.flows

import android.os.Bundle
import android.view.View
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FlowFindBeerByBarcodePresenter
import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeFragment
import com.ilya4.beerplease.presentation.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FindBeerByBarcodeMvpView
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class FindBeerByBarcodeFlowFragment: BaseFragment<FlowFindBeerByBarcodePresenter>(R.layout.activity_find_profile_beer_by_barcode), FindBeerByBarcodeMvpView {

    @InjectPresenter
    lateinit var presenter: FlowFindBeerByBarcodePresenter
    @ProvidePresenter
    override fun providePresenter(): FlowFindBeerByBarcodePresenter {
        return super.providePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = ScanBarcodeFragment()
        childFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onBackPressed() {
        presenter.backToMain()
    }
}