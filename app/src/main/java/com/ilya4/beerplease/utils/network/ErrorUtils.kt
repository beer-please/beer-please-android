package com.ilya4.beerplease.utils.network

import com.google.gson.Gson
import com.ilya4.beerplease.domain.entity.RestError
import okhttp3.ResponseBody
import timber.log.Timber
import java.io.IOException

class ErrorUtils {

    companion object {
        fun parseError(gson: Gson, body: ResponseBody?): RestError {
            lateinit var restError: RestError
            try {
                restError = gson.fromJson(body?.charStream(), RestError::class.java)
            } catch (e: IllegalStateException) {
                try {
                    Timber.d("GET NOT JSON RESPONSE: %s", body?.string())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return restError
        }
    }
}