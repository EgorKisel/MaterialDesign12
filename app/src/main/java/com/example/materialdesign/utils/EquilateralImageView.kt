package com.example.materialdesign.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class EquilateralImageView(
    context: Context,
    attributeSet: AttributeSet?=null,
    defStyleAttributeSet: Int=0) : AppCompatImageView(context, attributeSet, defStyleAttributeSet) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}