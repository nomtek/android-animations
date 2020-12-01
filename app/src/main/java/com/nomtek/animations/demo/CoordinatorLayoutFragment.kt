package com.nomtek.animations.demo

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.behavior.SwipeDismissBehavior
import com.nomtek.animations.R
import kotlinx.android.synthetic.main.fragment_coordinator_layout.*
import java.util.*

class CoordinatorLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_coordinator_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.title = "Title"
        fab.setOnClickListener {
            if (!customMenuView.isVisible) {
                animateEnterCustomMenuView()
            }
        }
        val params = textView.layoutParams as CoordinatorLayout.LayoutParams
        params.behavior = SwipeDismissBehavior<TextView>()
        textView.requestLayout()
    }

    private fun animateEnterCustomMenuView() {
        customMenuView.isVisible = true
        customMenuView.animate()
            .translationY(0f)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            activity?.runOnUiThread {
                                animateExitCustomMenu()
                            }
                        }
                    }, 3000L)
                }
            })
            .start()
    }

    private fun animateExitCustomMenu() {
        customMenuView.animate()
            .translationY(customMenuView.height.toFloat())
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    customMenuView.isVisible = false
                }
            })
            .start()
    }
}