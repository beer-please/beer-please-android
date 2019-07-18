package com.ilya4.beerplease.presentation.di.module

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ilya4.beerplease.data.io.model.RestApi
import com.ilya4.beerplease.presentation.app.Constants
import com.ilya4.beerplease.utils.UriDeserializer

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder()
        .registerTypeAdapter(Uri::class.java, UriDeserializer())
        .create()

    @Provides
    @Singleton
    fun provideRestApi(gson: Gson, client: OkHttpClient) : RestApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("")
            .client(client)
            .build()
            .create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() : OkHttpClient {
        val httpLoggingInterceptor  = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(true)
            .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)

        return builder.build()
    }


}