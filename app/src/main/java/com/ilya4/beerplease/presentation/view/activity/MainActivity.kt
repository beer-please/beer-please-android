package com.ilya4.beerplease.presentation.view.activity

import android.os.Bundle
import android.view.View
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AMainPresenter
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.AMainMvpView
import dagger.android.AndroidInjection
import javax.inject.Inject

import androidx.lifecycle.LifecycleOwner
import com.ilya4.beerplease.presentation.view.fragment.ScanBarcodeFragment
import com.ilya4.beerplease.presentation.view.fragment.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity: BaseActivity(), AMainMvpView {

    @Inject
    lateinit var presenter: AMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtonListeners()
        val fragment = SearchFragment().newInstance(Bundle())
        showFragment(SearchFragment.TAG, fragment, false)
    }

    private fun initButtonListeners() {
        scanFab.setOnClickListener { startFindProfileBeerActivity() }
    }

    private fun startFindProfileBeerActivity() {
        startActivity(FindProfileBeerByBarcodeActivity::class.java, false)
    }
}