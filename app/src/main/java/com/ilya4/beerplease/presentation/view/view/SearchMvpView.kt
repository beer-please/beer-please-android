package com.ilya4.beerplease.presentation.view.view

import com.ilya4.beerplease.domain.entity.search.SearchItem
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchMvpView: MvpView {
    fun updateSearchResults(searchResults: List<SearchItem>)
    fun clearSearchResults()
    fun showResultNotFound(show: Boolean)
    fun setBeerForResultNotFound(nameBeer: String)
    fun showSearchClearBtn(show: Boolean)
    fun hideKeyboard()
    fun setSearchHistory(searchHistory: ArrayList<String>)
    fun clearSearchHistory()
}