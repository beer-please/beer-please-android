package com.ilya4.beerplease.presentation.view.activity.base;


import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.ilya4.beerplease.R;

import dagger.Lazy;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import moxy.MvpAppCompatActivity;
import moxy.MvpPresenter;
import timber.log.Timber;

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

    public void startActivity(Class activity, boolean isFinish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (isFinish)
            finish();
    }

    @Deprecated
    public void showFragment(String key, Fragment fragment, boolean addToBackStack) {
        showFragment(key, fragment, R.id.container, addToBackStack);
    }
    @Deprecated
    public void showFragment(String key, Fragment fragment, int containerId, boolean addToBackStack) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
      FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();

        if (!isFinishing()) {
            if (!fragment.isAdded()) {
                fragmentTransaction.replace(containerId, fragment, key);
            }
            fragmentTransaction.show(fragment);

            if (addToBackStack)
                fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commitAllowingStateLoss();
            try {
                supportFragmentManager.executePendingTransactions();
            } catch (IllegalStateException e) {
                Timber.d(e);
            }
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingSupportAndroidInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}