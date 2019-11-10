package com.ilya4.beerplease.presentation.moxy.view.activity

import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.moxy.presenter.MoxyPresenter
import com.ilya4.beerplease.presentation.moxy.view.view.MoxyView
import kotlinx.android.synthetic.main.activity_moxy.*
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter

class MoxyActivity: MvpAppCompatActivity(R.layout.activity_moxy), MoxyView {

    @InjectPresenter
    lateinit var presenter: MoxyPresenter

    override fun updateText(msg: String) {
        mainText.text = msg
    }
}