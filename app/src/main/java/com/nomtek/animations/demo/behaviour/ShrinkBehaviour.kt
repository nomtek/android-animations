package com.nomtek.animations.demo.behaviour

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nomtek.animations.demo.views.CustomMenu


class ShrinkBehavior(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<FloatingActionButton>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        dependency: View
    ): Boolean {
        return dependency is CustomMenu
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: FloatingActionButton,
        dependency: View
    ): Boolean {
        val translationY: Float = dependency.height - dependency.translationY
        val percentComplete: Float = 1 - translationY / dependency.height
        child.scaleX = percentComplete
        child.scaleY = percentComplete
        return false
    }
}