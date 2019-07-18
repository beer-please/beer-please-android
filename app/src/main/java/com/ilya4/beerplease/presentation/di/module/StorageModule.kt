package com.ilya4.beerplease.presentation.di.module


import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class StorageModule {

    @Provides
    @Named("CommonPrefs")
    fun provideCommmonSharedPreferences(context: Context) : SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)
}