package com.nomtek.animations.demo.views

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.util.AttributeSet
import android.view.ViewPropertyAnimator
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.fragment_side_panel_layout.view.*

class SidePanelView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    private var animator: ViewPropertyAnimator? = null

    fun onSidePanelVisible() {
        animateText("Menu opened")
    }

    fun onSidePanelHidden() {
        animateText("Menu closed")
    }

    private fun animateText(newText: String) {
        animator?.cancel()
        menuView.titleView.run {
            alpha = 1f
            animator = animate().alpha(0f).setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    text = newText
                    animate().alpha(1f).start()

                }

                override fun onAnimationCancel(animation: Animator?) {
                    super.onAnimationCancel(animation)
                    animation?.removeAllListeners()
                }
            }).apply { start() }
        }
    }

}