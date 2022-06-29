package com.example.materialdesign.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentCollapsingToolbarBinding

class CollapsingToolbarFragment: Fragment() {
    private var _binding: FragmentCollapsingToolbarBinding? = null
    private val binding: FragmentCollapsingToolbarBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCollapsingToolbarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CollapsingToolbarFragment()
    }
}