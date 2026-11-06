package com.flow.launcher.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.Activity
import android.content.Context
import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

object LaunchAnimator {

    // iOS-style spring: stiffness 300, damping 0.75
    private const val SPRING_STIFFNESS = 300f
    private const val SPRING_DAMPING = 0.75f

    fun animateAppOpen(iconView: View, onEnd: () -> Unit) {
        val scaleX = SpringAnimation(iconView, DynamicAnimation.SCALE_X, 0.85f).apply {
            spring.stiffness = SPRING_STIFFNESS
            spring.dampingRatio = SPRING_DAMPING
        }
        val scaleY = SpringAnimation(iconView, DynamicAnimation.SCALE_Y, 0.85f).apply {
            spring.stiffness = SPRING_STIFFNESS
            spring.dampingRatio = SPRING_DAMPING
            addEndListener { _, _, _, _ -> onEnd() }
        }
        scaleX.start()
        scaleY.start()
    }

    fun animateAppClose(iconView: View) {
        val scaleX = SpringAnimation(iconView, DynamicAnimation.SCALE_X, 1f).apply {
            spring.stiffness = SPRING_STIFFNESS
            spring.dampingRatio = SPRING_DAMPING
        }
        val scaleY = SpringAnimation(iconView, DynamicAnimation.SCALE_Y, 1f).apply {
            spring.stiffness = SPRING_STIFFNESS
            spring.dampingRatio = SPRING_DAMPING
        }
        scaleX.start()
        scaleY.start()
    }

    fun applyWindowTransition(activity: Activity) {
        activity.overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }

    fun animateDrawerOpen(view: View) {
        view.translationY = view.height.toFloat()
        view.animate()
            .translationY(0f)
            .setDuration(380)
            .setInterpolator(
                androidx.interpolator.view.animation.FastOutSlowInInterpolator()
            )
            .start()
    }

    fun animateDrawerClose(view: View, onEnd: () -> Unit) {
        view.animate()
            .translationY(view.height.toFloat())
            .setDuration(300)
            .setInterpolator(
                androidx.interpolator.view.animation.LinearOutSlowInInterpolator()
            )
            .withEndAction(onEnd)
            .start()
    }
}
