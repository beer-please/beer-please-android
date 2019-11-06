package com.ilya4.beerplease.presentation.app

import com.google.gson.Gson
import com.ilya4.beerplease.data.io.model.AsyncData

import com.ilya4.beerplease.data.io.model.RestApi
import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.domain.entity.Beer
import com.ilya4.beerplease.utils.network.ErrorUtils
import timber.log.Timber
import java.io.IOException

class RestManager(private val restApi: RestApi,
                  private val gson: Gson,
                  private val settingsDataSource : SettingsDataSource) {

    fun getBeerById(beerId: Int, asyncData: AsyncData<Beer>) {
        try {
            val response = restApi.getBeerById(beerId).execute()
            if(response.isSuccessful && response.body() != null) {
                Timber.d("getBeerById success")
                asyncData.onSuccess(response.body() as Beer)
            } else {
                Timber.d("getBeerById error: %s", response.message())
                asyncData.onError(ErrorUtils.parseError(gson, response.errorBody()))
            }
        } catch (e: IOException) {
            Timber.d(e)
            asyncData.onFailure(e)
        }
    }

    fun getBeerByBarcode(barcode: String, asyncData: AsyncData<Beer>) {
        try {
            val response = restApi.getBeerByBarcode(barcode).execute()
            if (response.isSuccessful && response.body() != null) {
                Timber.d("getBeerByBarcode success")
                asyncData.onSuccess(response.body() as Beer)
            } else {
                Timber.d("getBeerByBarcode error: %s", response.message())
                asyncData.onError(ErrorUtils.parseError(gson, response.errorBody()))
            }
        } catch (e: IOException) {
            Timber.d(e)
            asyncData.onFailure(e)
        }
    }

    fun searchBeers(query: String, asyncData: AsyncData<List<Beer>>) {
        searchBeers(query, 1, 15, asyncData)
    }

    fun searchBeers(query: String, page: Int, pageSize: Int, asyncData: AsyncData<List<Beer>>) {
        try {
            val response = restApi.searchBeers(query, page, pageSize).execute()
            if (response.isSuccessful && response.body() != null) {
                Timber.d("searchBeers success")
                asyncData.onSuccess(response.body() as List<Beer>)
            } else {
                Timber.d("searchBeers error: %s", response.message())
                asyncData.onError(ErrorUtils.parseError(gson, response.errorBody()))
            }
        } catch (e: IOException) {
            Timber.d(e)
            asyncData.onFailure(e)
        }
    }
}