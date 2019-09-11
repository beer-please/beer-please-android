package com.ilya4.beerplease.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.widget.NestedScrollView
import com.google.android.material.animation.AnimationUtils

class Utils {

    /**
     * Hide keyboard.
     *
     * @param activity activity
     */
    companion object {

        fun hideKeyboard(activity: AppCompatActivity, view: View) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        /**
         * Show keyboard.
         *
         * @param activity the activity
         */
        fun showKeyboard(activity: AppCompatActivity, view: View): Boolean {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
            return true
        }

        fun initScrollListenerForBottomView(nestedScrollView: NestedScrollView, views: Array<View>) {
            nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->

                val heights = IntArray(views.size)
                for (index in views.indices) {
                    heights[index] = views[index].measuredHeight + views[index].marginBottom
                }
                if (scrollY > oldScrollY) {
                    for (index in views.indices) {
                        views[index]
                            .animate()
                            .translationY(heights[index].toFloat())
                            .setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
                            .duration = 175L
                    }
                }
                else {
                    for (index in views.indices) {
                        views[index]
                            .animate()
                            .translationY(0f)
                            .setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR)
                            .duration = 225L
                    }
                }
            })
        }

    }
}