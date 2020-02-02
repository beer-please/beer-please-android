package com.ilya4.beerplease.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.ilya4.beerplease.presentation.app.Constants.ACTION
import com.ilya4.beerplease.presentation.app.Constants.DATA_KEY_1
import com.ilya4.beerplease.presentation.app.Constants.DATA_KEY_2
import com.ilya4.beerplease.presentation.base.BaseTabFragment.FragmentInteractionCallback
import java.util.*

class FragmentUtils {

    companion object {
        const val TAG_SEPARATOR = ":"

        /**
         * Add the initial fragment, in most cases the first tab in BottomNavigationView
         */
        fun addInitialTabFragment(fragmentManager: FragmentManager,
                                  tagStacks: MutableMap<String, Stack<String>>,
                                  tag: String,
                                  fragment: Fragment,
                                  layoutId: Int,
                                  shouldAddToStack: Boolean) {
            fragmentManager
                .beginTransaction()
                .add(layoutId, fragment, fragment.javaClass.name + TAG_SEPARATOR + fragment.hashCode())
                .commit()
            if (shouldAddToStack) tagStacks[tag]?.push(fragment.javaClass.name + TAG_SEPARATOR + fragment.hashCode())
        }

        /**
         * Add additional tab in BottomNavigationView on click, apart from the initial one and for the first time
         */
        fun addAdditionalTabFragment(fragmentManager: FragmentManager,
                                     tagStacks: MutableMap<String, Stack<String>>,
                                     tag: String,
                                     show: Fragment,
                                     hide: Fragment,
                                     layoutId: Int,
                                     shouldAddToStack: Boolean) {
            fragmentManager
                .beginTransaction()
                .add(layoutId, show, show.javaClass.name + TAG_SEPARATOR + show.hashCode())
                .show(show)
                .hide(hide)
                .commit()
            if (shouldAddToStack) tagStacks[tag]?.push(show.javaClass.name + TAG_SEPARATOR + show.hashCode())
        }

        /**
         * Hide previous and show current tab fragment if it has already been added
         * In most cases, when tab is clicked again, not for the first time
         */
        fun showHideTabFragment(fragmentManager: FragmentManager,
                                show: Fragment,
                                hide: Fragment) {
            fragmentManager
                .beginTransaction()
                .hide(hide)
                .show(show)
                .commit()
        }

        /**
         * Add fragment in the particular tab stack and show it, while hiding the one that was before
         */
        fun addShowHideFragment(fragmentManager: FragmentManager,
                                tagStacks: MutableMap<String, Stack<String>>,
                                tag: String,
                                show: Fragment,
                                hide: Fragment,
                                layoutId: Int,
                                shouldAddToStack: Boolean) {
            fragmentManager
                .beginTransaction()
                .add(layoutId, show, show.javaClass.name + TAG_SEPARATOR + show.hashCode())
                .show(show)
                .hide(hide)
                .commit()
            if (shouldAddToStack) tagStacks[tag]?.push(show.javaClass.name + TAG_SEPARATOR + show.hashCode())
        }

        fun removeFragment(fragmentManager: FragmentManager,
                           show: Fragment,
                           remove: Fragment) {
            fragmentManager
                .beginTransaction()
                .remove(remove)
                .show(show)
                .commit()
        }

        /**
         * Send action from fragment to activity
         */
        fun sendActionToActivity(action: String,
                                 tab: String,
                                 shouldAdd: Boolean,
                                 fragmentInteractionCallback: FragmentInteractionCallback) {
            val bundle = Bundle()
            bundle.putString(ACTION, action)
            bundle.putString(DATA_KEY_1, tab)
            bundle.putBoolean(DATA_KEY_2, shouldAdd)
            fragmentInteractionCallback.onFragmentInteractionCallback(bundle)
        }
    }
}