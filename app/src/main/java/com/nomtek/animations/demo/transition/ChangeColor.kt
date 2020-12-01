package com.nomtek.animations.demo.transition

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionValues


class ChangeColor : Transition() {
    /**
     * Convenience method: Add the background Drawable property value
     * to the TransitionsValues.value Map for a target.
     */
    private fun captureValues(values: TransitionValues) {
        // Capture the property values of views for later use
        values.values[PROPNAME_BACKGROUND] = values.view.background
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    // Capture the value of the background drawable property for a target in the ending Scene.
    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        // This transition can only be applied to views that are on both starting and ending scenes.
        if (null == startValues || null == endValues) {
            return null
        }
        // Store a convenient reference to the target. Both the starting and ending layout have the
        // same target.
        val view: View = endValues.view
        // Store the object containing the background property for both the starting and ending
        // layouts.
        val startBackground =
            startValues.values.get(PROPNAME_BACKGROUND) as Drawable?
        val endBackground =
            endValues.values.get(PROPNAME_BACKGROUND) as Drawable?
        // This transition changes background colors for a target. It doesn't animate any other
        // background changes. If the property isn't a ColorDrawable, ignore the target.
        if (startBackground is ColorDrawable && endBackground is ColorDrawable) {
            // If the background color for the target in the starting and ending layouts is
            // different, create an animation.
            if (startBackground.color != endBackground.color) {
                // Create a new Animator object to apply to the targets as the transitions framework
                // changes from the starting to the ending layout. Use the class ValueAnimator,
                // which provides a timing pulse to change property values provided to it. The
                // animation runs on the UI thread. The Evaluator controls what type of
                // interpolation is done. In this case, an ArgbEvaluator interpolates between two
                // #argb values, which are specified as the 2nd and 3rd input arguments.
                val animator = ValueAnimator.ofObject(
                    ArgbEvaluator(),
                    startBackground.color, endBackground.color
                )
                // Add an update listener to the Animator object.
                animator.addUpdateListener { animation ->
                    val value = animation.animatedValue
                    // Each time the ValueAnimator produces a new frame in the animation, change
                    // the background color of the target. Ensure that the value isn't null.
                    if (null != value) {
                        view.setBackgroundColor(value as Int)
                    }
                }
                // Return the Animator object to the transitions framework. As the framework changes
                // between the starting and ending layouts, it applies the animation you've created.
                return animator
            }
        }
        // For non-ColorDrawable backgrounds, we just return null, and no animation will take place.
        return null
    }

    companion object {
        /** Key to store a color value in TransitionValues object  */
        private const val PROPNAME_BACKGROUND = "customtransition:change_color:background"
    }
}