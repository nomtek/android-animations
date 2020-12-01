package com.nomtek.animations.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.core.view.doOnPreDraw
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.fragment.app.Fragment
import com.nomtek.animations.R
import kotlinx.android.synthetic.main.fragment_fling.*

class FlingFragment : Fragment() {

    private val flingAnimationX: FlingAnimation by lazy {
        FlingAnimation(droidImageView, DynamicAnimation.TRANSLATION_X)
    }

    private val flingAnimationY: FlingAnimation by lazy {
        FlingAnimation(droidImageView, DynamicAnimation.TRANSLATION_Y)
    }

    private val velocityTracker by lazy { VelocityTracker.obtain() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fling, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAnimation()
    }

    private fun setupAnimation() {
        requireView().doOnPreDraw {
            setupAnimationBounds()
        }
        flingAnimationX.friction = 0.3f
        flingAnimationY.friction = 0.3f
        setupTouchListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchListener() {
        var lastX = 0f
        var lastY = 0f
        droidImageView.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    velocityTracker.clear()
                    lastX = event.x
                    lastY = event.y
                    flingAnimationX.cancel()
                    flingAnimationY.cancel()
                    velocityTracker.addMovement(event)
                }
                MotionEvent.ACTION_MOVE -> {
                    velocityTracker.addMovement(event)
                    droidImageView.x =
                        (droidImageView.x + event.x - lastX).coerceIn(0f, getImageMaxXValue())
                    droidImageView.y =
                        (droidImageView.y + event.y - lastY).coerceIn(0f, getImageMaxYValue())
                }
                MotionEvent.ACTION_UP -> {
                    velocityTracker.addMovement(event)
                    velocityTracker.computeCurrentVelocity(1000)
                    flingAnimationX.setStartVelocity(velocityTracker.xVelocity * resources.displayMetrics.density / 2.0f)
                    flingAnimationY.setStartVelocity(velocityTracker.yVelocity * resources.displayMetrics.density / 2.0f)
                    flingAnimationX.start()
                    flingAnimationY.start()
                }
            }
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        flingAnimationX.cancel()
        flingAnimationY.cancel()
    }

    private fun setupAnimationBounds() {
        flingAnimationX.setMinValue(0f)
        flingAnimationY.setMinValue(0f)
        flingAnimationX.setMaxValue(getImageMaxXValue())
        flingAnimationY.setMaxValue(getImageMaxYValue())
    }

    private fun getImageMaxYValue() = (requireView().height - droidImageView.height).toFloat()

    private fun getImageMaxXValue() = (requireView().width - droidImageView.width).toFloat()

    companion object {
        val TAG = FlingFragment::class.java.simpleName
    }
}