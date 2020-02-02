package com.ilya4.beerplease.presentation.di.module


import android.content.ClipboardManager
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import com.ilya4.beerplease.domain.executor.JobExecutor
import com.ilya4.beerplease.domain.executor.PostExecutionThread
import com.ilya4.beerplease.domain.executor.ThreadExecutor
import com.ilya4.beerplease.presentation.app.BeerPleaseApp
import com.ilya4.beerplease.presentation.view.UIThread
import dagger.Module
import dagger.Provides
import io.reactivex.processors.BehaviorProcessor
import java.util.*
import javax.inject.Named
import javax.inject.Singleton

@Module
class AndroidModule {

    @Provides
    @Singleton
    fun provideContext(app: BeerPleaseApp) : Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context) : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideClipboardManager(context: Context) : ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    @Provides
    @Singleton
    fun providePackageManager(context: Context) : PackageManager = context.packageManager

    @Provides
    @Singleton
    fun provideBehaviourProcessor() : BehaviorProcessor<Boolean> = BehaviorProcessor.createDefault(false)

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor) : ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread) : PostExecutionThread = uiThread
}