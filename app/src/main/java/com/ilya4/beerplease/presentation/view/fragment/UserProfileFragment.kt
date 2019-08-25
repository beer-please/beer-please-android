package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FUserProfilePresenter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.FUserProfileMvpView
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class UserProfileFragment: BaseFragment(), FUserProfileMvpView {

    @Inject
    lateinit var presenter: FUserProfilePresenter

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
    }

    companion object{
        const val TAG = "UserProfileFragment"
    }
}