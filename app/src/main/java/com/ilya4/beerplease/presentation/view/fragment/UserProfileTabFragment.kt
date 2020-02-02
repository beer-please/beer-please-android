package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.UserProfilePresenter
import com.ilya4.beerplease.presentation.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.view.UserProfileMvpView
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class UserProfileTabFragment: BaseTabFragment<UserProfilePresenter>(R.layout.fragment_user_profile), UserProfileMvpView {

    @InjectPresenter
    lateinit var presenter: UserProfilePresenter
    @ProvidePresenter
    override fun providePresenter(): UserProfilePresenter {
        return super.providePresenter()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    companion object{
        const val TAG = "UserProfileTabFragment"
    }
}