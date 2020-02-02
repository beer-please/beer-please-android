package com.ilya4.beerplease.presentation.base;


import androidx.fragment.app.Fragment;

import dagger.Lazy;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import moxy.MvpAppCompatActivity;
import moxy.MvpPresenter;

import javax.inject.Inject;

public abstract class BaseActivity<Presenter extends MvpPresenter> extends MvpAppCompatActivity implements HasSupportFragmentInjector, HasFragmentInjector {

    public BaseActivity(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Inject
    protected Lazy<Presenter> daggerPresenter;

    protected Presenter providePresenter() {
        return daggerPresenter.get();
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingSupportAndroidInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingSupportAndroidInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}