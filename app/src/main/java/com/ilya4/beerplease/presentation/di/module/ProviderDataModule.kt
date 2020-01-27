package com.ilya4.beerplease.presentation.di.module



import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.data.repository.local.AddBeerTempRepository
import com.ilya4.beerplease.data.repository.local.SettingsLocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ProviderDataModule {

    @Provides
    @Singleton
    fun provideSettingsDataSource(settingsLocalDataSource: SettingsLocalDataSource) : SettingsDataSource = settingsLocalDataSource

    @Provides
    @Singleton
    fun provideAddBeerTempRepository(): AddBeerTempRepository = AddBeerTempRepository()
}