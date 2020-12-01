package com.nomtek.animations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.nomtek.animations.demo.*
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressButton.setOnClickListener {
            showFragment(ProgressButtonFragment())
        }
        transformationButton.setOnClickListener {
            showFragment(TransformationFragment())
        }
        springButton.setOnClickListener {
            showFragment(SpringFragment())
        }
        flingButton.setOnClickListener {
            showFragment((FlingFragment()))
        }
        layoutChangesButton.setOnClickListener {
            showFragment(LayoutChangesFragment())
        }
        motionLayoutButton.setOnClickListener {
            showFragment(MotionLayoutFragment())
        }
        constraintSetButton.setOnClickListener {
            showFragment(ConstraintSetFragment())
        }
        otherMotionLayoutButton.setOnClickListener {
            showFragment(OtherMotionLayoutFragment())
        }
        sidePanelButton.setOnClickListener {
            showFragment(SidePanelFragment())
        }
        sceneButton.setOnClickListener {
            showFragment(SceneFragment())
        }
        activityTransitionButton.setOnClickListener {
            showFragment(ActivityTransitionFragment())
        }
        vectorDrawableButton.setOnClickListener {
            showFragment(VectorDrawableFragment())
        }
        transitionLayoutChangesButton.setOnClickListener {
            showFragment(TransitionLayoutChangesFragment())
        }
        coordinatorLayoutButton.setOnClickListener {
            showFragment(CoordinatorLayoutFragment())
        }
        bottomSheetButton.setOnClickListener {
            showFragment(BottomSheetFragment())
        }
    }

    private fun showFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragmentContainer, fragment, null)
        }
    }
}