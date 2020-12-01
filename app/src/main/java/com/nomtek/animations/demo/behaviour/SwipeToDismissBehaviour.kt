package com.nomtek.animations.demo.behaviour

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.customview.widget.ViewDragHelper

class SwipeToDismissBehaviour<V : View> : CoordinatorLayout.Behavior<V> {

    private var dragHelper: ViewDragHelper? = null
    private var interceptingEvents = false
    private var parentRight = 0

    private val callback = object : ViewDragHelper.Callback() {

        private val INVALID_POINTER_ID = -1
        private var currentPointer = INVALID_POINTER_ID

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return currentPointer == INVALID_POINTER_ID || pointerId == currentPointer
        }

        override fun onViewCaptured(child: View, activePointerId: Int) {
            currentPointer = activePointerId
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return child.width
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return if (left < 0) {
                0
            } else {
                left
            }
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return child.top
        }

        override fun onViewReleased(child: View, xvel: Float, yvel: Float) {
            currentPointer = INVALID_POINTER_ID
            val left = if (xvel <= 0f) {
                0
            } else {
                parentRight
            }
            dragHelper?.settleCapturedViewAt(left, child.top)
            child.postOnAnimation(RecursiveSettle(child))
        }

        override fun onViewPositionChanged(child: View, left: Int, top: Int, dx: Int, dy: Int) {
            child.alpha = 1 - left / parentRight.toFloat()
        }
    }

    @Suppress("unused")
    constructor() : super()

    @Suppress("unused")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: V,
        ev: MotionEvent
    ): Boolean {
        var isIntercept = interceptingEvents
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                isIntercept = parent.isPointInChildBounds(child, ev.x.toInt(), ev.y.toInt())
                interceptingEvents = isIntercept
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                interceptingEvents = false
            }
        }
        return if (isIntercept) {
            helper(parent).shouldInterceptTouchEvent(ev)
        } else false
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: V, ev: MotionEvent): Boolean {
        parentRight = parent.right
        val helper = helper(parent)
        return if (helper.capturedView == child || helper.isViewUnder(child, ev.x.toInt(), ev.y.toInt())) {
            helper.processTouchEvent(ev)
            true
        } else {
            false
        }
    }

    private fun helper(parent: ViewGroup): ViewDragHelper {
        val dHelper = dragHelper ?: ViewDragHelper.create(parent, callback)
        dragHelper = dHelper
        return dHelper
    }

    private inner class RecursiveSettle(private val child: View) : Runnable {

        override fun run() {
            if (dragHelper?.continueSettling(true) == true) {
                child.postOnAnimation(this)
            } else {
                if (child.left == parentRight) {
                    child.isVisible = false
                }
                child.removeCallbacks(this)
            }
        }
    }

}