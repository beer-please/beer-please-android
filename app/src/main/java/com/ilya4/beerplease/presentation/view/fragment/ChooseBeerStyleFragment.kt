package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FChooseBeerStylePresenter
import com.ilya4.beerplease.presentation.view.adapter.BeerStyleAdapter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FChooseBeerStyleMvpView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_choose_beer_style.*
import javax.inject.Inject

class ChooseBeerStyleFragment: BaseFragment(), FChooseBeerStyleMvpView {

    @Inject
    lateinit var presenter: FChooseBeerStylePresenter

    private val adapter: BeerStyleAdapter = BeerStyleAdapter()

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
        setupBeerStyleAdapter()
        setupOnClickListeners()
        presenter.init()
        presenter.setSearchDebounce(searchEt)
    }

    override fun showSearchClearBtn(show: Boolean) {
        searchClearButton.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun updateStyleList(styleList: List<String>) {
        adapter.setStyleBeerList(styleList)
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