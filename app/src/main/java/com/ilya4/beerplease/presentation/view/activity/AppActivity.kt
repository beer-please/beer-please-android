package com.ilya4.beerplease.presentation.view.activity

import android.os.Bundle
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AppPresenter
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.AppMvpView
import dagger.android.AndroidInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class AppActivity: BaseActivity<AppPresenter>(R.layout.layout_container), AppMvpView {

    @InjectPresenter
    lateinit var presenter: AppPresenter
    @ProvidePresenter
    override fun providePresenter(): AppPresenter {
        return super.providePresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initFirstScreen()
    }

    private fun initFirstScreen() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFlowFragment())
            .commit()
    }
}