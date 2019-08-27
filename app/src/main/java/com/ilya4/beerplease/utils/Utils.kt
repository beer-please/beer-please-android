package com.ilya4.beerplease.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

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

    }
}