package com.ilya4.beerplease.presentation.view.fragment.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BaseFragment extends Fragment {

    protected FragmentInteractionCallback fragmentInteractionCallback;
    protected static String currentTab;

    private static final String TAG = "BaseFragment";

    public static final String FRAGMENT_ID = "FRAGMENT_ID";

    private int fragmentId = -1;

    public BaseFragment newInstance(Bundle args) {
        BaseFragment fragment = this;
        if (args != null) {
            fragmentId = args.getInt(FRAGMENT_ID);
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        try {
//            fragmentInteractionCallback = (FragmentInteractionCallback) context;
//        }catch (ClassCastException e) {
//            throw new RuntimeException(context.toString() + " must implement " + FragmentInteractionCallback.class.getName());
//        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface FragmentInteractionCallback {

        void onFragmentInteractionCallback(Bundle bundle);
    }

    public static void setCurrentTab(String currentTab) {
        BaseFragment.currentTab = currentTab;
    }
}
