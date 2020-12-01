package com.nomtek.animations.demo.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout

class ContentView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var foregroundColor: Int = Color.RED
    set(value) {
        field = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(foregroundColor)
    }
}