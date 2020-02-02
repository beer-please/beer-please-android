package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilya4.beerplease.R
import com.ilya4.beerplease.domain.entity.search.SearchItem
import com.ilya4.beerplease.presentation.presenter.SearchPresenter
import com.ilya4.beerplease.presentation.view.fragment.flows.MainFlowFragment
import com.ilya4.beerplease.presentation.view.adapter.SearchAdapter
import com.ilya4.beerplease.presentation.view.adapter.SearchHistoryAdapter
import com.ilya4.beerplease.presentation.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.listener.OnSearchHistoryItemClickListener
import com.ilya4.beerplease.presentation.view.listener.OnSearchItemClickListener
import com.ilya4.beerplease.presentation.view.view.SearchMvpView
import com.ilya4.beerplease.utils.Utils
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.item_search_input.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class SearchTabFragment: BaseTabFragment<SearchPresenter>(R.layout.fragment_search), SearchMvpView, OnSearchHistoryItemClickListener, OnSearchItemClickListener {

    @InjectPresenter
    lateinit var presenter: SearchPresenter
    @ProvidePresenter
    override fun providePresenter(): SearchPresenter {
        return super.providePresenter()
    }

    private val searchAdapter = SearchAdapter(this)

    private val searchHistoryAdapter = SearchHistoryAdapter(this)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSearch()
        initRecyclerView()
        initButtonListeners()
    }

    override fun updateSearchResults(searchResults: List<SearchItem>) {
        searchAdapter.setSearchItems(searchResults)
    }

    override fun clearSearchResults() {
        searchAdapter.clearSearchItems()
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
        Utils.hideKeyboard(requireContext(), searchEt)
    }

    override fun setSearchHistory(searchHistory: ArrayList<String>) {
        searchHistoryAdapter.setSearchHistoryItems(searchHistory)
    }

    override fun clearSearchHistory() {
        searchHistoryAdapter.clearSearchHistoryItems()
    }

    override fun onSearchItemClick(searchItem: SearchItem) {
        val bundle = Bundle()
        Utils.hideKeyboard(requireActivity() as AppCompatActivity, searchEt)
        (requireParentFragment() as MainFlowFragment).showBeerCardFragment(currentTab, true, bundle)
    }

    override fun onSearchHistoryItemClick(query: String) {
        searchHistoryAdapter.clearSearchHistoryItems()
        searchEt.setText(query)
        searchEt.setSelection(query.length)
    }

    private fun initSearch() {
        presenter.setDebounce(searchEt)
        searchEt.requestFocus()
        searchEt.setOnEditorActionListener { tv, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH && tv.text.toString().trim().length > 1) {
                presenter.getSearchResults(tv.text.toString())
                presenter.saveQueryToSearchHistory()
                return@setOnEditorActionListener true
            }
            hideKeyboard()
            return@setOnEditorActionListener false
        }
    }

    private fun initRecyclerView() {
        searchRv.adapter = searchAdapter
        searchRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        searchHistoryRv.adapter = searchHistoryAdapter
        searchHistoryRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initButtonListeners() {
        searchClearButton.setOnClickListener {
            searchEt.setText("")
            clearSearchResults()
            showResultNotFound(false)
            showSearchClearBtn(false)
        }
        searchEt.setOnClickListener {
            if (searchEt.text.toString() == "")
                presenter.requestShowSearchHistory()
        }
        addBeerButton.setOnClickListener {
            val activity = parentFragment as MainFlowFragment
            val bundle = Bundle()
            Utils.hideKeyboard(requireActivity() as AppCompatActivity, searchEt)
            activity.showAddNewBeerFragment(currentTab,true, bundle)
        }
    }

}