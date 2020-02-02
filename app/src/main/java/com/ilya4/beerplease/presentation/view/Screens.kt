package com.ilya4.beerplease.presentation.view

import androidx.fragment.app.Fragment
import com.ilya4.beerplease.presentation.view.fragment.flows.FindBeerByBarcodeFlowFragment
import com.ilya4.beerplease.presentation.view.fragment.flows.MainFlowFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object MainFlow: SupportAppScreen() {
        override fun getFragment(): Fragment =
            MainFlowFragment()
    }

    object FindBeerByBarcodeFlow: SupportAppScreen() {
        override fun getFragment(): Fragment =
            FindBeerByBarcodeFlowFragment()
    }
}