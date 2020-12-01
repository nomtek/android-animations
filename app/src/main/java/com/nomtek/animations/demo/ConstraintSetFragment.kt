package com.nomtek.animations.demo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.nomtek.animations.R
import com.nomtek.animations.demo.transition.ChangeColor
import com.transitionseverywhere.ChangeText
import kotlinx.android.synthetic.main.fragment_constraint_set.*

class ConstraintSetFragment : Fragment() {

    private var secondSetDisplayed = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_constraint_set, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimations()
    }

    private fun setupAnimations() {
        val animateFunc = {
            val transitionSet = createTransitionSet()
            TransitionManager.beginDelayedTransition(root, transitionSet)
            applyLayoutChanges()
        }
        imageView.setOnClickListener {
            animateFunc()
        }
        animateButton.setOnClickListener {
            animateFunc()
        }
    }

    private fun applyLayoutChanges() {
        imageView.background = ColorDrawable(
            if (secondSetDisplayed) {
                Color.RED
            } else Color.YELLOW
        )
        textView.text = if (secondSetDisplayed) {
            "Short text"
        } else {
            "Long text. Ta da!!!!"
        }
        val constraint =
            if (secondSetDisplayed) {
                ConstraintSet().apply { clone(requireContext(), R.layout.fragment_constraint_set) }
            } else {
                ConstraintSet().apply {
                    clone(requireContext(), R.layout.fragment_constraint_set_alt)
                }
            }
        constraint.applyTo(root)
        secondSetDisplayed = !secondSetDisplayed
    }

    private fun createTransitionSet() = TransitionSet().apply {
        ordering = TransitionSet.ORDERING_TOGETHER
        addTransition(Slide())
        addTransition(ChangeBounds())
        addTransition(Fade(Fade.IN))
        addTransition(ChangeColor())
        addTransition(ChangeText().apply {
            changeBehavior = ChangeText.CHANGE_BEHAVIOR_OUT_IN
        })
        duration = 1000
    }
}