package com.ilya4.beerplease.utils

import android.content.Context
import android.util.DisplayMetrics

class ViewUtils {

    companion object {

        fun convertDpToPixel(dp: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        /**
         * This method converts device specific pixels to density independent pixels.
         *
         * @param px      A value in px (pixels) unit. Which we need to convert into db
         * @param context Context to get resources and device specific display metrics
         * @return A float value to represent dp equivalent to px value
         */
        fun convertPixelsToDp(px: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }

        fun convertSpToPixels(sp: Float, context: Context): Float {
            val resources = context.resources
            val metrics = resources.displayMetrics
            return sp * (metrics.scaledDensity / DisplayMetrics.DENSITY_DEFAULT)
        }

    }
}