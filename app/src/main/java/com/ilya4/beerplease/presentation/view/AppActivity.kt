package com.ilya4.beerplease.presentation.view

import android.os.Bundle
import com.ilya4.beerplease.R
import com.ilya4.beerplease.presentation.presenter.AppPresenter
import com.ilya4.beerplease.presentation.base.BaseActivity
import com.ilya4.beerplease.presentation.base.BaseFragment
import com.ilya4.beerplease.presentation.view.view.AppMvpView
import dagger.android.AndroidInjection
import moxy.MvpPresenter
import moxy.MvpView
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

class AppActivity: BaseActivity<AppPresenter>(R.layout.layout_container), AppMvpView {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    @InjectPresenter
    lateinit var presenter: AppPresenter
    @ProvidePresenter
    override fun providePresenter(): AppPresenter {
        return super.providePresenter()
    }

    private val navigator = SupportAppNavigator(this, R.id.container)

    private val currentFragment: BaseFragment<out MvpPresenter<out MvpView>>?
        get() = supportFragmentManager.findFragmentById(R.id.container) as BaseFragment<out MvpPresenter<out MvpView>>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        navigator.applyCommands(arrayOf<Command>(Replace(Screens.MainFlow)))
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        currentFragment?.onBackPressed() ?: super.onBackPressed()
    }
}