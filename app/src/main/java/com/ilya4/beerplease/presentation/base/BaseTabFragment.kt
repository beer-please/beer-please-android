package com.ilya4.beerplease.presentation.base

import android.os.Bundle
import com.ilya4.beerplease.presentation.app.Constants.EXTRA_IS_ROOT_FRAGMENT
import moxy.MvpPresenter
import moxy.MvpView

abstract class BaseTabFragment<Presenter: MvpPresenter<out MvpView>>(contentLayoutId: Int)
    : BaseFragment<Presenter>(contentLayoutId) {



    interface FragmentInteractionCallback {
        fun onFragmentInteractionCallback(bundle: Bundle)
    }

    fun newInstance(args: Bundle?, isRoot: Boolean) : BaseTabFragment<Presenter> {
        val bundle = Bundle()
        bundle.putBoolean(EXTRA_IS_ROOT_FRAGMENT, isRoot)
        args?.let {
            bundle.putAll(it)
        }
        val fragment = this
        fragment.arguments = bundle
        return fragment
    }

    companion object {
        lateinit var currentTab: String
    }
}