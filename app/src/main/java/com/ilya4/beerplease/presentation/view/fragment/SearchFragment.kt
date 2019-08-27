package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilya4.beerplease.R
import com.ilya4.beerplease.domain.entity.search.SearchItem
import com.ilya4.beerplease.presentation.presenter.FSearchPresenter
import com.ilya4.beerplease.presentation.view.activity.MainActivity
import com.ilya4.beerplease.presentation.view.adapter.SearchAdapter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FSearchMvpView
import com.ilya4.beerplease.utils.Utils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment: BaseFragment(), FSearchMvpView {

    @Inject
    lateinit var presenter: FSearchPresenter

    private val adapter = SearchAdapter()

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
        initSearch()
        initRecyclerView()
        initButtonListeners()
    }

    override fun updateSearchResults(searchResults: List<SearchItem>) {
        adapter.setSearchItems(searchResults)
    }

    override fun clearSearchResults() {
        adapter.clearSearchItems()
    }

    override fun showResultNotFound(show: Boolean) {
        searchBeerNotFound.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun setBeerForResultNotFound(nameBeer: String) {
        titleNotFound.text = getString(R.string.search_title_not_found, nameBeer)
    }

    override fun showSearchClearBtn(show: Boolean) {
        searchClearButton.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun hideKeyboard() {
        Utils.hideKeyboard(activity as MainActivity, searchEt)
    }

    private fun initSearch() {
        presenter.setDebounce(searchEt)
        searchEt.requestFocus()
    }

    private fun initRecyclerView() {
        searchRv.adapter = adapter
        searchRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initButtonListeners() {
        searchClearButton.setOnClickListener {
            searchEt.setText("")
            clearSearchResults()
            showResultNotFound(false)
            showSearchClearBtn(false)
        }
    }

    companion object{
        const val TAG = "SearchFragment"
    }
}