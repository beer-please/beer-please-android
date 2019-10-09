package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FChooseBreweryPresenter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FChooseBreweryMvpView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ChooseBreweryFragment: BaseFragment(), FChooseBreweryMvpView {

    @Inject
    lateinit var presenter: FChooseBreweryPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_brewery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
    }
}