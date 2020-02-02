package com.ilya4.beerplease.presentation.presenter

import android.widget.EditText
import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.domain.usecase.DefaultObserver
import com.ilya4.beerplease.domain.usecase.search.SearchUseCase
import com.ilya4.beerplease.presentation.view.component.EditTextDebounce
import com.ilya4.beerplease.presentation.view.view.SearchMvpView
import moxy.InjectViewState
import moxy.MvpPresenter

@InjectViewState
class SearchPresenter(private val searchUseCase: SearchUseCase,
                      private val settingsDataSource: SettingsDataSource):
    MvpPresenter<SearchMvpView>() {

    private lateinit var searchDebounce: EditTextDebounce
    private lateinit var query: String

    fun setDebounce(searchEt: EditText) {
        searchDebounce = EditTextDebounce.create(searchEt)
        searchDebounce.watch(
            {
                if (it.trim().isNotEmpty()) {
                    viewState.showSearchClearBtn(true)
                    viewState.clearSearchHistory()
                } else {
                    viewState.showSearchClearBtn(false)
                    viewState.showResultNotFound(false)
                }
                if (it.trim().length > 2) {
                getSearchResults(it)
            } else {
                    viewState.clearSearchResults()
            }
        }, 400)
    }

    fun getSearchResults(query: String) {
        this.query = query
        searchUseCase.execute(SearchObserver(), SearchUseCase.Params.forSearchBeers(query))
    }

    fun saveQueryToSearchHistory() {
        var searchHistoryList = settingsDataSource.getSearchHistory()?.toMutableList()
        if (searchHistoryList == null)
            searchHistoryList = ArrayList()
        if (searchHistoryList.contains(query)) {
            searchHistoryList.remove(query)
        }
        searchHistoryList.add(query)
        settingsDataSource.setSearchHistory(searchHistoryList)
    }

    fun requestShowSearchHistory() {
        val searchHistorySet = settingsDataSource.getSearchHistory()?.toMutableList()
        if (searchHistorySet != null)
            viewState.setSearchHistory(searchHistorySet as ArrayList<String>)
    }

    inner class SearchObserver: DefaultObserver<SearchUseCase.Result>() {
        override fun onNext(result: SearchUseCase.Result) {
            if (result.searchResult != null && result.searchResult.isNotEmpty()) {
                viewState.updateSearchResults(result.searchResult)
                viewState.showResultNotFound(false)
            } else {
                viewState.clearSearchResults()
                viewState.showResultNotFound(true)
                viewState.setBeerForResultNotFound(query)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        searchUseCase.dispose()
    }
}