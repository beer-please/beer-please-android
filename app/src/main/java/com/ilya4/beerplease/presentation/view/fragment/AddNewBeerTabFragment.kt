package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AddNewBeerPresenter
import com.ilya4.beerplease.presentation.view.fragment.flows.MainFlowFragment
import com.ilya4.beerplease.presentation.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.view.AddNewBeerMvpView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_add_new_beer.*
import kotlinx.android.synthetic.main.fragment_beer_card.mainContent
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AddNewBeerTabFragment: BaseTabFragment<AddNewBeerPresenter>(R.layout.fragment_add_new_beer), AddNewBeerMvpView {

    @InjectPresenter
    lateinit var presenter: AddNewBeerPresenter
    @ProvidePresenter
    override fun providePresenter(): AddNewBeerPresenter {
        return super.providePresenter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStart() {
        super.onStart()
        presenter.getCacheData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnClickListeners()
        presenter.init()

        fixHideBottomBarOnScroll()
        initToolbar()
    }

    override fun setBeerStyle(beerStyle: String) {
        styleInput.setInputText(beerStyle)
    }

    private fun fixHideBottomBarOnScroll() {
        val activity = activity as MainFlowFragment
        activity.initOnScrollListener(mainContent)
    }

    private fun initToolbar() {
        addBeerToolbar.setNavigationOnClickListener {  activity?.onBackPressed()}
    }

    private fun setupOnClickListeners() {
        styleInput.setOnClickListener {
            val activity = activity as MainFlowFragment
            val bundle = Bundle()
            activity.showChooseBeerStyleFragment(currentTab, true, bundle)
        }
    }
}