package com.ilya4.beerplease.data.repository.local

import android.content.SharedPreferences
import com.ilya4.beerplease.data.repository.SettingsDataSource

import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class SettingsLocalDataSource @Inject constructor(@Named("CommonPrefs") val sharedPreferences: SharedPreferences) :
    SettingsDataSource {

}