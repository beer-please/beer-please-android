package com.ilya4.beerplease.presentation.view.fragment.base

import moxy.MvpAppCompatFragment
import moxy.MvpPresenter
import moxy.MvpView
import javax.inject.Inject
import dagger.Lazy


abstract class BaseFlowFragment<Presenter : MvpPresenter<out MvpView>>(contentLayoutId: Int) :
    MvpAppCompatFragment(contentLayoutId) {

    @Inject
    protected lateinit var daggerPresenter: Lazy<Presenter>

    protected open fun providePresenter(): Presenter =
        daggerPresenter.get()

    open fun onBackPressed() { }
}
