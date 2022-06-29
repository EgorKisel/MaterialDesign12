package com.example.materialdesign.view

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.example.materialdesign.databinding.FragmentTankBinding


class TankFragment: Fragment() {

    private var flag = true

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
        tanksTransaction()
    }

    private fun tanksTransaction() {
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
        binding.myTank2.setOnClickListener {
            val transitionExplode = Explode()
            val rect = Rect()
            it.getGlobalVisibleRect(rect)
            transitionExplode.epicenterCallback = object : Transition.EpicenterCallback(){
                override fun onGetEpicenter(transition: Transition): Rect {
                    return rect
                }

            }
            binding.myTank.visibility = View.GONE
            binding.myTank3.visibility = View.GONE
            binding.myTank4.visibility = View.GONE
            binding.myTank5.visibility = View.GONE
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

// Пробую переместить танк в место клика
//    private var xDelta = 0
//    private  var yDelta = 0
//    private val touchListener = OnTouchListener { view, event ->
//        val x = event.rawX.toInt()
//        val y = event.rawY.toInt()
//        when (event.action and MotionEvent.ACTION_MASK) {
//            MotionEvent.ACTION_MOVE -> {
//                if (x - xDelta + view.width <= binding.myTank.getWidth() && y - yDelta + view.height <= container.getHeight() && x - xDelta >= 0 && y - yDelta >= 0) {
//                    val layoutParams = view.layoutParams as FrameLayout.LayoutParams
//                    layoutParams.leftMargin = x - xDelta
//                    layoutParams.topMargin = y - yDelta
//                    layoutParams.rightMargin = 0
//                    layoutParams.bottomMargin = 0
//                    view.layoutParams = layoutParams
//                }
//            }
//        }
//        binding.myTank.invalidate()
//        true
//    }

}