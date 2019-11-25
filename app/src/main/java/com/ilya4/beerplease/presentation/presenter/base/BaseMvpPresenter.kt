package com.ilya4.beerplease.presentation.presenter.base

import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import moxy.MvpPresenter
import moxy.MvpView

abstract class BaseMvpPresenter<V: MvpView>: MvpPresenter<V>() {

}