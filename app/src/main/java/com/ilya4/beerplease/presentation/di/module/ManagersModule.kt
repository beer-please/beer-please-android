package com.ilya4.beerplease.presentation.di.module


import com.google.gson.Gson
import com.ilya4.beerplease.data.io.model.RestApi
import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.presentation.app.RestManager

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ManagersModule {

    @Provides
    @Singleton
    fun provideRestManager(restApi: RestApi, gson: Gson, settingsDataSource: SettingsDataSource) : RestManager {
        return RestManager(restApi, gson, settingsDataSource)
    }
}