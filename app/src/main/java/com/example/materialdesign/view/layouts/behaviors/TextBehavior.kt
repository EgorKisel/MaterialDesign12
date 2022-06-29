package com.example.materialdesign.view.layouts.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class TextBehavior(context: Context, attr: AttributeSet?=null):
    CoordinatorLayout.Behavior<View>(context,attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppCompatImageView
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        if(dependency is AppCompatImageView){
            child.alpha = 1- dependency.x
            child.alpha = 1- abs(dependency.x*2/dependency.height.toFloat())
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}