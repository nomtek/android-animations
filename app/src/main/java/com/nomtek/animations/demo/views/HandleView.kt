package com.nomtek.animations.demo.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class HandleView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {

    var radius: Float = 0f

    private val paint by lazy {
        Paint().apply {
            color = Color.BLUE
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius * width / 2, paint)
    }

}