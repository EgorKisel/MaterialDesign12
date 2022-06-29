package com.example.materialdesign.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.ChangeBounds
import androidx.transition.Explode
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.materialdesign.databinding.FragmentTankBinding


class TankFragment: Fragment() {

    private var flag = false

    private var _binding: FragmentTankBinding? = null
    private val binding: FragmentTankBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tankTransaction()

    }

    private fun tankTransaction() {
        binding.myTank.setOnClickListener {
            val myTransitionSet = TransitionSet()
            val myTransition = Explode()
            myTransition.duration = 2000L
            myTransitionSet.addTransition(myTransition)
            val cb = ChangeBounds()
            cb.duration = 5000L
            myTransitionSet.addTransition(cb)
            TransitionManager.beginDelayedTransition(binding.root, myTransitionSet)
            flag = !flag
            if (flag){
                binding.myTank.visibility = View.VISIBLE
            }else{
                binding.myTank.visibility = View.GONE
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            TankFragment()
    }

//    private var xDelta = 0
//    private  var yDelta = 0
//    private val touchListener = OnTouchListener { view, event ->
//        val x = event.rawX.toInt()
//        val y = event.rawY.toInt()
//        when (event.action and MotionEvent.ACTION_MASK) {
//            MotionEvent.ACTION_DOWN -> {
//                val lParams = view.layoutParams as FrameLayout.LayoutParams
//                xDelta = x - lParams.leftMargin
//                yDelta = y - lParams.topMargin
//            }
//            MotionEvent.ACTION_MOVE -> {
//                if (x - xDelta + view.width <= binding.tankGame.getWidth() && y - yDelta + view.height <= container.getHeight() && x - xDelta >= 0 && y - yDelta >= 0) {
//                    val layoutParams = view.layoutParams as FrameLayout.LayoutParams
//                    layoutParams.leftMargin = x - xDelta
//                    layoutParams.topMargin = y - yDelta
//                    layoutParams.rightMargin = 0
//                    layoutParams.bottomMargin = 0
//                    view.layoutParams = layoutParams
//                }
//            }
//        }
//        binding.tankGame.invalidate()
//        true
//    }

}