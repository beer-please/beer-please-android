package com.ilya4.beerplease.presentation.view.activity.base;


import android.content.Intent;
import androidx.annotation.CallSuper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.ilya4.beerplease.R;
import com.michaelflisar.rxbus2.interfaces.IRxBusQueue;
import com.michaelflisar.rxbus2.rx.RxDisposableManager;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;
import io.reactivex.processors.BehaviorProcessor;
import org.reactivestreams.Publisher;
import timber.log.Timber;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity implements IRxBusQueue, HasSupportFragmentInjector, HasFragmentInjector {

    private final BehaviorProcessor<Boolean> resumedProcessor = BehaviorProcessor.createDefault(false);

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingSupportAndroidInjector;
    @Inject
    DispatchingAndroidInjector<android.app.Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onResume() {
        resumedProcessor.onNext(true);
        super.onResume();
    }

    @Override
    protected void onPause() {
        resumedProcessor.onNext(false);
        super.onPause();
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        RxDisposableManager.unsubscribe(this);
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (IllegalStateException e) {
            supportFinishAfterTransition();
        }
    }

    public void startActivity(Class activity, boolean isFinish) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        if (isFinish)
            finish();
    }

    public void startActivityWithExtra(Intent intent, boolean isFinish) {
        startActivity(intent);
        if (isFinish)
            finish();
    }

    public void startActivityWithClearStack(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void startActivityWithExtraAndClearStack(Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void showFragment(String key, Fragment fragment, boolean addToBackStack) {
        showFragment(key, fragment, R.id.container, addToBackStack);
    }

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

    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean isBusResumed() {
        return resumedProcessor.getValue();
    }

    @Override
    public Publisher<Boolean> getResumeObservable() {
        return resumedProcessor;
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