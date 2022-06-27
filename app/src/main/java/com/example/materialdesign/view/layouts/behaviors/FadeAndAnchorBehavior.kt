package com.example.materialdesign.view.layouts.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class FadeAndAnchorBehavior(context: Context, attr: AttributeSet?=null):
    CoordinatorLayout.Behavior<View>(context,attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {

        if(dependency is AppBarLayout){
            child.alpha = 1- abs(dependency.y*2/dependency.height.toFloat())
            child.y = dependency.height.toFloat()+dependency.y
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}