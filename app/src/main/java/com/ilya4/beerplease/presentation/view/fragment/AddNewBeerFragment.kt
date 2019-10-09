package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FAddNewBeerPresenter
import com.ilya4.beerplease.presentation.view.activity.MainActivity
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FAddNewBeerMvpView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_add_new_beer.*
import kotlinx.android.synthetic.main.fragment_beer_card.*
import kotlinx.android.synthetic.main.fragment_beer_card.mainContent
import javax.inject.Inject

class AddNewBeerFragment: BaseFragment(), FAddNewBeerMvpView {

    @Inject
    lateinit var presenter: FAddNewBeerPresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_new_beer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        presenter.init()

        fixHideBottomBarOnScroll()
        initToolbar()
    }

    private fun fixHideBottomBarOnScroll() {
        val activity = activity as MainActivity
        activity.initOnScrollListener(mainContent)
    }

    private fun initToolbar() {
        addBeerToolbar.setNavigationOnClickListener {  activity?.onBackPressed()}
    }

    private fun setupOnClickListeners() {
        styleInput.setOnClickListener {
            val activity = activity as MainActivity
            val bundle = Bundle()
            activity.showChooseBeerStyleFragment(getCurrentTab(), true, bundle)
        }
    }
}