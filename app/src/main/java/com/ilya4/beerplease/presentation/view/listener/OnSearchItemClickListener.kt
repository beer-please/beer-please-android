package com.ilya4.beerplease.presentation.view.listener

import com.ilya4.beerplease.domain.entity.search.SearchItem

interface OnSearchItemClickListener {
    fun onSearchItemClick(searchItem: SearchItem)
}