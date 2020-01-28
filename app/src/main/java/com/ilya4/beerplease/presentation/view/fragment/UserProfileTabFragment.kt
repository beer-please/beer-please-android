package com.ilya4.beerplease.presentation.view.fragment

import android.content.Context
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.FUserProfilePresenter
import com.ilya4.beerplease.presentation.view.fragment.base.BaseTabFragment
import com.ilya4.beerplease.presentation.view.view.FUserProfileMvpView
import dagger.android.support.AndroidSupportInjection
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter

class UserProfileTabFragment: BaseTabFragment<FUserProfilePresenter>(R.layout.fragment_user_profile), FUserProfileMvpView {

    @InjectPresenter
    lateinit var presenter: FUserProfilePresenter
    @ProvidePresenter
    override fun providePresenter(): FUserProfilePresenter {
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