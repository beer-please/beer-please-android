package com.ilya4.beerplease.presentation.app

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import com.ilya4.beerplease.data.repository.SettingsDataSource
import com.ilya4.beerplease.presentation.di.component.AppComponent
import com.ilya4.beerplease.presentation.di.component.DaggerAppComponent
import com.ilya4.beerplease.presentation.di.module.ManagersModule
import com.ilya4.beerplease.presentation.di.module.NetworkModule
import com.ilya4.beerplease.presentation.di.module.ProviderDataModule
import com.ilya4.beerplease.presentation.di.module.StorageModule


import com.michaelflisar.rxbus2.interfaces.IRxBusQueue
import dagger.android.*
import io.reactivex.processors.BehaviorProcessor
import org.reactivestreams.Publisher
import timber.log.Timber
import javax.inject.Inject

class BeerPleaseApp : Application(), IRxBusQueue, HasActivityInjector, HasServiceInjector {

    companion object {
        private lateinit var appContext: Context

        fun getAppContext() = appContext
    }

    private val behaviorProcessor = BehaviorProcessor.createDefault(false)

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
        behaviorProcessor.onNext(true)
        appContext = applicationContext
        initTimber()
    }

    fun createComponent() : AppComponent{
            component = DaggerAppComponent
                .builder()
                .app(this)
                .moduleManager(ManagersModule())
                .moduleNetwork(NetworkModule())
                .moduleProvide(ProviderDataModule())
                .moduleStorage(StorageModule())
                .build()

        return component
    }

    override fun isBusResumed(): Boolean {
       return behaviorProcessor.value ?: false
    }

    override fun getResumeObservable(): Publisher<Boolean> {
        return behaviorProcessor
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