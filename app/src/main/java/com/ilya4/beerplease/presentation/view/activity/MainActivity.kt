package com.ilya4.beerplease.presentation.view.activity

import android.os.Bundle
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AMainPresenter
import com.ilya4.beerplease.presentation.view.activity.base.BaseActivity
import com.ilya4.beerplease.presentation.view.view.AMainMvpView
import dagger.android.AndroidInjection
import javax.inject.Inject


import com.ilya4.beerplease.presentation.view.fragment.SearchFragment
import com.ilya4.beerplease.presentation.view.fragment.UserProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity: BaseActivity(), AMainMvpView  {

    @Inject
    lateinit var presenter: AMainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButtonListeners()

        showSearchFragment()
    }

    private fun initButtonListeners() {
        scanFab.setOnClickListener { startFindProfileBeerActivity() }

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.search_item -> {
                    showSearchFragment()
                    true
                }
                R.id.profile_item -> {
                    showUserProfileFragment()
                    true
                }
                else -> false
            }
        }
    }

    private fun startFindProfileBeerActivity() {
        startActivity(FindProfileBeerByBarcodeActivity::class.java, false)
    }

    private fun showSearchFragment() {
        val bundle = Bundle()
        showFragment(SearchFragment.TAG, bundle)
    }

    private fun showUserProfileFragment() {
        val bundle = Bundle()
        showFragment(UserProfileFragment.TAG, bundle)
    }

    private fun showFragment(key: String, bundle: Bundle) {
        var fragmentCached = supportFragmentManager.findFragmentByTag(key)
        var addToBackStack = false
        if (fragmentCached == null) {
            when(key) {
                SearchFragment.TAG -> {
                    fragmentCached = SearchFragment().newInstance(bundle)
                    addToBackStack = false
                }
                UserProfileFragment.TAG -> {
                    fragmentCached = UserProfileFragment().newInstance(bundle)
                    addToBackStack = false
                }
            }
        } else {
            Objects.requireNonNull(fragmentCached.arguments)!!.putAll(bundle)
        }
        super.showFragment(key, fragmentCached, addToBackStack)
    }
}