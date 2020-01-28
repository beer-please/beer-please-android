package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FChooseBreweryPresenter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.view.FChooseBreweryMvpView
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ChooseBreweryTabFragment: BaseTabFragment<FChooseBreweryPresenter>(R.layout.fragment_choose_brewery), FChooseBreweryMvpView {

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