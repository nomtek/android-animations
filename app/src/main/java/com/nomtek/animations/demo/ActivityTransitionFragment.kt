package com.nomtek.animations.demo

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Pair
import androidx.fragment.app.Fragment
import com.nomtek.animations.R
import kotlinx.android.synthetic.main.fragment_activity_transition.*

class ActivityTransitionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity_transition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val intent = Intent(context, TransitionActivity::class.java)
        imageView.setOnClickListener {
            val pairs: MutableList<Pair<View, String>> =
                ArrayList()
            pairs.add(Pair(imageView, imageView.transitionName))
            startActivity(
                intent,
                ActivityOptions.makeSceneTransitionAnimation(
                    requireActivity(),
                    *pairs.toTypedArray()
                ).toBundle()
            )
        }
    }
}