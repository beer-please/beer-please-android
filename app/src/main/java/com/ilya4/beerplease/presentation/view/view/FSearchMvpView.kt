package com.ilya4.beerplease.presentation.view.view

import com.ilya4.beerplease.domain.entity.search.SearchItem

interface FSearchMvpView: MvpView {
    fun updateSearchResults(searchResults: List<SearchItem>)
    fun clearSearchResults()
}