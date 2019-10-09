package com.ilya4.beerplease.data.io.model

import com.ilya4.beerplease.domain.entity.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestApi {

    @GET("v1/beers/{id}")
    fun getBeerById(@Path("id") beerId: Int): Call<Beer>
}