package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.ChooseBreweryPresenter
import com.ilya4.beerplease.presentation.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.view.ChooseBreweryMvpView
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ChooseBreweryTabFragment: BaseTabFragment<ChooseBreweryPresenter>(R.layout.fragment_choose_brewery), ChooseBreweryMvpView {

    @InjectPresenter
    lateinit var presenter: ChooseBreweryPresenter
    @ProvidePresenter
    override fun providePresenter(): ChooseBreweryPresenter {
        return super.providePresenter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}