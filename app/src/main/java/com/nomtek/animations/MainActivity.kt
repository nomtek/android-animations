package com.nomtek.animations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Transition
import androidx.fragment.app.commit
import com.skydoves.transformationlayout.onTransformationStartContainer

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        setupTransitionAnimations()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fragmentContainer, MenuFragment(), null)
            }
        }
    }

    private fun setupTransitionAnimations() {
        val transition: Transition = AutoTransition()
        transition.excludeTarget(android.R.id.statusBarBackground, true)
        transition.excludeTarget(android.R.id.navigationBarBackground, true)
        window.exitTransition = transition
        window.enterTransition = transition
    }
}