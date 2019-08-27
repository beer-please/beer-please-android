package com.ilya4.beerplease.data.repository.local

import android.content.SharedPreferences
import com.google.gson.Gson
import com.ilya4.beerplease.data.repository.SettingsDataSource

import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class SettingsLocalDataSource @Inject constructor(@Named("CommonPrefs") val sharedPreferences: SharedPreferences) :
    SettingsDataSource {

    override fun setSearchHistory(searchHistoryList: MutableList<String>) {
        val gson = Gson()
        val json = gson.toJson(searchHistoryList)

        sharedPreferences.edit().putString(SEARCH_HISTORY, json).apply()
    }

    override fun getSearchHistory(): MutableList<String>? {
        val gson = Gson()
        val json = sharedPreferences.getString(SEARCH_HISTORY, null)
        var list: List<String> = ArrayList()
        if(json != null)
            list = gson.fromJson(json, Array<String>::class.java).toList()

        return list.toMutableList()
    }

    companion object{
        const val SEARCH_HISTORY = "SEARCH_HISTORY"
    }
}