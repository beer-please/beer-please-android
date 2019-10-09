package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FChooseBeerStylePresenter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class ChooseBeerStyleFragment: BaseFragment(), FChooseBeerStyleMvpView {

    @Inject
    lateinit var presenter: FChooseBeerStylePresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_beer_style, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
    }
}