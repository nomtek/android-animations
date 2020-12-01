package com.nomtek.animations.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.fragment.app.Fragment
import com.nomtek.animations.R
import com.nomtek.animations.demo.utils.convertDpToPixel
import kotlinx.android.synthetic.main.fragment_spring.*

class SpringFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_spring, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        squareAnimationButton.setOnClickListener {
            squareView.translationY = 0f
            createSpringAnimation(
                view = squareView,
                property = SpringAnimation.Y,
                finalPosition = squareView.x + squareView.height.toFloat() + convertDpToPixel(
                    100f,
                    requireContext()
                ),
                stiffness = SpringForce.STIFFNESS_LOW,
                dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
            ).start()
        }
    }

    private fun createSpringAnimation(
        view: View,
        property: DynamicAnimation.ViewProperty,
        finalPosition: Float,
        stiffness: Float,
        dampingRatio: Float
    ): SpringAnimation {
        val animation = SpringAnimation(view, property)
        val spring = SpringForce(finalPosition)
        spring.stiffness = stiffness
        spring.dampingRatio = dampingRatio
        animation.spring = spring
        return animation
    }
}