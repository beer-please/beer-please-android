package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FChooseBreweryPresenter
import com.ilya4.beerplease.presentation.presenter.FScanBarcodePresenter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FChooseBreweryMvpView
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class ChooseBreweryFragment: BaseFragment<FChooseBreweryPresenter>(R.layout.fragment_choose_brewery), FChooseBreweryMvpView {

    @InjectPresenter
    lateinit var presenter: FChooseBreweryPresenter
    @ProvidePresenter
    override fun providePresenter(): FChooseBreweryPresenter {
        return super.providePresenter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}