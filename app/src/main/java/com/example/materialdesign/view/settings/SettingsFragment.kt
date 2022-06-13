package com.example.materialdesign.view.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.materialdesign.databinding.FragmentPictureOfTheDayBinding
import com.example.materialdesign.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.chip1.setOnClickListener {
            Toast.makeText(context, "setOnClickListener", Toast.LENGTH_SHORT).show()
        }
        binding.chip2.setOnClickListener {
            Toast.makeText(context, "setOnClickListener", Toast.LENGTH_SHORT).show()
        }
        binding.chip3.setOnClickListener {
            Toast.makeText(context, "setOnClickListener", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment()
    }
}