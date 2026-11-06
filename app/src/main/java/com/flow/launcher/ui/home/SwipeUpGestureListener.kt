package com.flow.launcher.ui.home

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs

class SwipeUpGestureListener(
    context: Context,
    private val onSwipeUp: () -> Unit
) : View.OnTouchListener {

    private val gestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val e1 = e1 ?: return false
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x

            if (abs(diffY) > abs(diffX) &&
                abs(diffY) > SWIPE_THRESHOLD &&
                abs(velocityY) > SWIPE_VELOCITY_THRESHOLD &&
                diffY < 0
            ) {
                onSwipeUp()
                return true
            }
            return false
        }
    })

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
}
