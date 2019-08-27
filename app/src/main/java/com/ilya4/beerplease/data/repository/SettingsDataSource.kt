package com.ilya4.beerplease.data.repository

interface SettingsDataSource {

    fun setSearchHistory(searchHistoryList: MutableList<String>)
    fun getSearchHistory(): MutableList<String>?
}