package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FChooseBeerStylePresenter
import com.ilya4.beerplease.presentation.view.adapter.BeerStyleAdapter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.listener.OnBeerStyleClickListener
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_choose_beer_style.*
import kotlinx.android.synthetic.main.item_search_input.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class ChooseBeerStyleTabFragment: BaseTabFragment<FChooseBeerStylePresenter>(R.layout.fragment_choose_beer_style),
    FChooseBeerStyleMvpView, OnBeerStyleClickListener {

    @InjectPresenter
    lateinit var presenter: FChooseBeerStylePresenter
    @ProvidePresenter
    override fun providePresenter(): FChooseBeerStylePresenter {
        return super.providePresenter()
    }

    private val adapter: BeerStyleAdapter = BeerStyleAdapter(this)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBeerStyleAdapter()
        setupOnClickListeners()
        presenter.init(requireContext())
        presenter.setSearchDebounce(searchEt)
    }

    override fun showSearchClearBtn(show: Boolean) {
        searchClearButton.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun updateStyleList(styleList: List<String>) {
        adapter.setStyleBeerList(styleList)
    }

    override fun onChooseBeerStyle(style: String) {
        presenter.chooseBeerStyle(style)
    }

    override fun closeFragment() {
        activity?.onBackPressed()
    }

    private fun setupBeerStyleAdapter() {
        stylesRv.adapter = adapter
        stylesRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun setupOnClickListeners() {
        searchClearButton.setOnClickListener {
            searchEt.setText("")
            presenter.requestToClearSearchInput()
        }
        backBtn.setOnClickListener { activity?.onBackPressed() }
    }
}