package com.ilya4.beerplease.presentation.view.fragment.base;

import android.os.Bundle;

import javax.inject.Inject;

import dagger.Lazy;
import moxy.MvpAppCompatFragment;
import moxy.MvpPresenter;

import static com.ilya4.beerplease.presentation.app.Constants.EXTRA_IS_ROOT_FRAGMENT;


public abstract class BaseFragment<Presenter extends MvpPresenter> extends MvpAppCompatFragment {

    @Inject
    protected Lazy<Presenter> daggerPresenter;

    protected Presenter providePresenter() {
        return daggerPresenter.get();
    }

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    protected FragmentInteractionCallback fragmentInteractionCallback;
    protected static String currentTab;

    public BaseFragment newInstance(Bundle args, boolean isRoot) {
        BaseFragment fragment = this;
        if (args != null) {
            args.putBoolean(EXTRA_IS_ROOT_FRAGMENT, isRoot);
            fragment.setArguments(args);
        } else {
            Bundle bundle = new Bundle();
            bundle.putBoolean(EXTRA_IS_ROOT_FRAGMENT, isRoot);
            fragment.setArguments(bundle);
        }
        return fragment;
    }

    public interface FragmentInteractionCallback {

        void onFragmentInteractionCallback(Bundle bundle);
    }

    public static void setCurrentTab(String currentTab) {
        BaseFragment.currentTab = currentTab;
    }

    public static String getCurrentTab() {
        return currentTab;
    }
}
