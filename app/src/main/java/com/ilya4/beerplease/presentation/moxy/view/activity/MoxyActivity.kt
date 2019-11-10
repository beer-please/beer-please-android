package com.ilya4.beerplease.presentation.moxy.view.activity

import android.os.Bundle
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.moxy.presenter.MoxyPresenter
import com.ilya4.beerplease.presentation.moxy.view.view.MoxyView
import dagger.Lazy
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_moxy.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

class MoxyActivity: MvpAppCompatActivity(R.layout.activity_moxy), MoxyView {

    @Inject
    lateinit var daggerPresenter: Lazy<MoxyPresenter>

    @InjectPresenter
    lateinit var presenter: MoxyPresenter

    @ProvidePresenter
    fun providePresenter() : MoxyPresenter = daggerPresenter.get()

    override fun updateText(msg: String) {
        mainText.text = msg
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }
}