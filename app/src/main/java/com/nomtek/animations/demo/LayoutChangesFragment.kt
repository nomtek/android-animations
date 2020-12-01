package com.nomtek.animations.demo

import android.animation.Animator
import android.animation.LayoutTransition
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.nomtek.animations.R
import kotlinx.android.synthetic.main.fragment_layout_changes.*
import java.util.*

class LayoutChangesFragment : Fragment() {

    private var customAnimations = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_layout_changes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addViewButton.setOnClickListener {
            addView()
        }
        removeViewButton.setOnClickListener {
            removeView()
        }
        setupLayoutTransition(customAnimations)
        animationStyleButton.setOnClickListener {
            customAnimations = !customAnimations
            setupLayoutTransition(customAnimations)
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun setupLayoutTransition(custom: Boolean = false) {
        val layoutTransition = LayoutTransition()
        layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        if (custom) {
            val slideIn: Animator = ObjectAnimator.ofPropertyValuesHolder(
                null as Any?,
                PropertyValuesHolder.ofFloat(
                    "translationY",
                    -resources.getDimensionPixelSize(R.dimen.view_height).toFloat(),
                    0f
                )
            )
            layoutTransition.setAnimator(LayoutTransition.APPEARING, slideIn)
            val slideOut: Animator = ObjectAnimator.ofPropertyValuesHolder(
                null as Any?,
                PropertyValuesHolder.ofFloat(
                    "translationY",
                    0f,
                    -2 * resources.getDimensionPixelSize(R.dimen.view_height).toFloat()
                )
            )
            layoutTransition.setAnimator(LayoutTransition.DISAPPEARING, slideOut)
        }
        viewsContainer.layoutTransition = layoutTransition
    }

    private fun addView() {
        val childView = View(requireContext()).apply {
            setBackgroundColor(getRandomColor())
        }
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            resources.getDimensionPixelSize(R.dimen.view_height)
        )
        viewsContainer.addView(childView, 0, params)
        Handler().postDelayed({
            params.height = resources.getDimensionPixelSize(R.dimen.view_height) * 2
            params.width = resources.displayMetrics.widthPixels - 100
            childView.layoutParams = params
        }, 400)
    }

    private fun removeView() {
        val childCount = viewsContainer.childCount
        if (childCount > 0) {
            viewsContainer.removeViewAt(0)
        }
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(
            255,
            rnd.nextInt(256),
            rnd.nextInt(256),
            rnd.nextInt(256)
        )
    }
}