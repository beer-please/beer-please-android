package com.ilya4.beerplease.presentation.app

import android.app.Activity
import android.app.Application
import android.app.Service
import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.presentation.di.component.AppComponent
import com.ilya4.beerplease.presentation.di.component.DaggerAppComponent
import com.ilya4.beerplease.presentation.di.module.*


import dagger.android.*
import moxy.MvpFacade
import timber.log.Timber
import javax.inject.Inject

class BeerPleaseApp : Application(), HasActivityInjector, HasServiceInjector {

    @Inject
    lateinit var dispatchingAndroidInjectorActivity: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingAndroidInjectorService: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var settingsDataSource: SettingsDataSource

    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        createComponent().inject(this)
        initTimber()
        MvpFacade.init()
    }

    private fun createComponent() : AppComponent{
            component = DaggerAppComponent
                .builder()
                .app(this)
                .moduleManager(ManagersModule())
                .moduleNetwork(NetworkModule())
                .moduleProvide(ProviderDataModule())
                .moduleStorage(StorageModule())
                .moduleNavigation(NavigationModule())
                .build()

        return component
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjectorActivity
    }

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingAndroidInjectorService
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}