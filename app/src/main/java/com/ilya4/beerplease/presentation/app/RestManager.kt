package com.ilya4.beerplease.presentation.app

import com.google.gson.Gson

import com.ilya4.beerplease.data.io.model.RestApi
import com.ilya4.beerplease.data.repository.SettingsDataSource

class RestManager(val restApi: RestApi, val gson: Gson, val settingsDataSource : SettingsDataSource) {
}