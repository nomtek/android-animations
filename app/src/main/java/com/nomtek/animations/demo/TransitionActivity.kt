package com.nomtek.animations.demo

import android.os.Bundle
import android.transition.*
import androidx.appcompat.app.AppCompatActivity
import com.nomtek.animations.R

class TransitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivityTransition()
        setContentView(R.layout.activity_transition)
    }

    private fun setupActivityTransition() {
        val autoTransition: Transition = TransitionSet().apply {
            ordering = TransitionSet.ORDERING_TOGETHER
            addTransition(ChangeBounds())
            addTransition(Explode().addTarget(R.id.titleView))
            addTransition(Fade().addTarget(R.id.descriptionView))
            duration = 300
        }
        autoTransition.excludeTarget(android.R.id.statusBarBackground, true)
        autoTransition.excludeTarget(android.R.id.navigationBarBackground, true)
        window.exitTransition = autoTransition
        window.enterTransition = autoTransition
    }
}