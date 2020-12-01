package com.nomtek.animations.demo

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nomtek.animations.R
import com.skydoves.transformationlayout.TransformationCompat
import kotlinx.android.synthetic.main.fragment_transformation.*

class TransformationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transformation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            transformationLayout.startTransform()
        }

        myCardView.setOnClickListener {
            transformationLayout.finishTransform()
        }

        fabActivity.setOnClickListener {
            val intent = Intent(context, TransformationDetailActivity::class.java)
            TransformationCompat.startActivity(transformationActivityLayout, intent)
        }
    }
}