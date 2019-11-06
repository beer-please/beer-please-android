package com.ilya4.beerplease.data.io.model

import com.ilya4.beerplease.domain.entity.Beer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApi {

    @GET("v1/beers/{id}")
    fun getBeerById(@Path("id") beerId: Int): Call<Beer>
    @GET("v1/beers/{barcode}")
    fun getBeerByBarcode(@Path("barcode") barcode: String): Call<Beer>
    @GET("v1/beers")
    fun searchBeers(@Query("name") query: String, @Query("page") page: Int, @Query("size") pageSize: Int): Call<List<Beer>>
}