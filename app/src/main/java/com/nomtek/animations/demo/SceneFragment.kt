package com.nomtek.animations.demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.TransitionManager
import com.nomtek.animations.R
import kotlinx.android.synthetic.main.fragment_scene_layout.*

class SceneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scene_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sceneRoot: ViewGroup = view.findViewById(R.id.scene_root)
        val originalScene =
            Scene.getSceneForLayout(sceneRoot, R.layout.scene_a, requireContext()).apply {
                enter()
            }
        val anotherScene: Scene =
            Scene.getSceneForLayout(sceneRoot, R.layout.scene_b, requireContext())
        var targetScene = anotherScene
        animateButton.setOnClickListener {
            TransitionManager.go(targetScene, AutoTransition())
            targetScene = if (targetScene == originalScene) {
                anotherScene
            } else {
                originalScene
            }
        }
    }
}