package com.nomtek.animations.demo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.nomtek.animations.R
import com.nomtek.animations.demo.transition.ChangeColor
import kotlinx.android.synthetic.main.fragment_transition_changes_layout.*

class TransitionLayoutChangesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transition_changes_layout, container, false)
    }

    private var state = ViewState.Start

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animateButton.setOnClickListener {
            val transition = TransitionSet().apply {
                ordering = TransitionSet.ORDERING_SEQUENTIAL
                addTransition(ChangeColor())
                addTransition(Explode().addTarget(secondImageView))
                addTransition(Fade().addTarget(imageView).setStartDelay(1000))
                addTransition(ChangeBounds().addTarget(animateButton))
            }
            TransitionManager.beginDelayedTransition(view as ViewGroup, transition)
            state = when (state) {
                ViewState.Start -> {
                    animateFinishState()
                    ViewState.Finish
                }
                ViewState.Finish -> {
                    animateStartState()
                    ViewState.Start
                }
            }
            view.requestLayout()
        }
    }

    private fun animateFinishState() {
        val params = animateButton.layoutParams as (FrameLayout.LayoutParams)
        params.gravity = Gravity.BOTTOM or Gravity.END
        params.width = resources.getDimensionPixelSize(R.dimen.button_width)
        params.height = resources.getDimensionPixelSize(R.dimen.button_height)
        imageView.isVisible = false
        secondImageView.isVisible = false
        container.addView(
            Button(requireContext()).apply {
                text = "test"
            },
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
        )
        container.background = ColorDrawable(Color.MAGENTA)
    }

    private fun animateStartState() {
        val params = animateButton.layoutParams as (FrameLayout.LayoutParams)
        params.gravity = Gravity.TOP or Gravity.START
        params.width = FrameLayout.LayoutParams.WRAP_CONTENT
        params.height = FrameLayout.LayoutParams.WRAP_CONTENT
        imageView.isVisible = true
        secondImageView.isVisible = true
        container.removeAllViews()
        container.background = ColorDrawable(Color.WHITE)
    }

    enum class ViewState {
        Start, Finish
    }
}