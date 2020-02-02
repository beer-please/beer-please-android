package com.ilya4.beerplease.presentation.di.component


import com.ilya4.beerplease.presentation.app.BeerPleaseApp
import com.ilya4.beerplease.presentation.di.module.*

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    BuildersModule::class,
    ManagersModule::class,
    ProviderDataModule::class,
    StorageModule::class,
    NavigationModule::class]
)
interface AppComponent {
    fun inject(app: BeerPleaseApp)

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun app(app: BeerPleaseApp) : Builder

        @BindsInstance
        fun moduleNetwork(module: NetworkModule) : Builder

        @BindsInstance
        fun moduleManager(module: ManagersModule) : Builder

        @BindsInstance
        fun moduleProvide(module: ProviderDataModule) : Builder

        @BindsInstance
        fun moduleStorage(module: StorageModule) : Builder

        @BindsInstance
        fun moduleNavigation(module: NavigationModule) : Builder

        fun build() : AppComponent
    }
}