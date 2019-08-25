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
import com.ilya4.beerplease.presentation.view.adapter.SearchAdapter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FSearchMvpView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment: BaseFragment(), FSearchMvpView {

    @Inject
    lateinit var presenter: FSearchPresenter

    val adapter = SearchAdapter()

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
    }

    override fun updateSearchResults(searchResults: List<SearchItem>) {
        adapter.setSearchItems(searchResults)
    }

    override fun clearSearchResults() {
        adapter.clearSearchItems()
    }

    private fun initSearch() {
        presenter.setDebounce(searchEt)
        searchEt.requestFocus()
    }

    private fun initRecyclerView() {
        searchRv.adapter = adapter
        searchRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    companion object{
        const val TAG = "SearchFragment"
    }
}