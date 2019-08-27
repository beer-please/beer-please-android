package com.ilya4.beerplease.presentation.view.component

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom
import com.google.android.material.animation.AnimationUtils

class HideViewOnScrollWithMarginBottomBehavior<V: View>(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<V>(context, attrs) {

    private val ENTER_ANIMATION_DURATION = 225
    private val EXIT_ANIMATION_DURATION = 175
    private val STATE_SCROLLED_DOWN = 1
    private val STATE_SCROLLED_UP = 2
    private var height = 0
    private var currentState = 2
    private var currentAnimator: ViewPropertyAnimator? = null

    override fun onLayoutChild(parent: CoordinatorLayout, child: V, layoutDirection: Int): Boolean {
        this.height = child.measuredHeight + child.marginBottom
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int
    ): Boolean {
        return nestedScrollAxes == 2
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        if (this.currentState != 1 && dyConsumed > 0) {
            this.slideDown(child)
        } else if (this.currentState != 2 && dyConsumed < 0) {
            this.slideUp(child)
        }

    }

    protected fun slideUp(child: V) {
        if (this.currentAnimator != null) {
            this.currentAnimator!!.cancel()
            child.clearAnimation()
        }

        this.currentState = 2
        this.animateChildTo(child, 0, 225L, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR)
    }

    protected fun slideDown(child: V) {
        if (this.currentAnimator != null) {
            this.currentAnimator!!.cancel()
            child.clearAnimation()
        }

        this.currentState = 1
        this.animateChildTo(
            child,
            this.height,
            175L,
            AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR
        )
    }

    private fun animateChildTo(
        child: V,
        targetY: Int,
        duration: Long,
        interpolator: TimeInterpolator
    ) {
        this.currentAnimator =
            child.animate().translationY(targetY.toFloat()).setInterpolator(interpolator)
                .setDuration(duration).setListener(object :
                    AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        this@HideViewOnScrollWithMarginBottomBehavior.currentAnimator = null
                    }
                })
    }
}