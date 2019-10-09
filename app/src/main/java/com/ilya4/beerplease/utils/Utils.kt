package com.ilya4.beerplease.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginBottom
import androidx.core.widget.NestedScrollView
import com.google.android.material.animation.AnimationUtils
import org.json.JSONArray
import timber.log.Timber
import java.nio.charset.Charset

class Utils {

        companion object {
            /**
             * Hide keyboard.
             *
             * @param activity activity
             */

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

            fun loadJsonStringFromAsset(context: Context, assetName: String): String? {
                try {
                    val json = loadStringFromAsset(context, assetName)
                    json.let {
                        return json
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                }
                return null
            }

            @Throws(Exception::class)
            private fun loadStringFromAsset(context: Context, assetName: String): String {
                val inputStream = context.assets.open(assetName)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                return String(buffer, Charset.forName("UTF-8"))
            }
    }
}