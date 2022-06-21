package com.example.materialdesign.view.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.materialdesign.R
import com.example.materialdesign.databinding.FragmentSettingsBinding
import com.example.materialdesign.utils.Parameters

const val KEY_SETTINGS = "KEY_SETTINGS"
const val KEY_THEME = "KEY_THEME"
const val THEME_RED = 0
const val THEME_PINK = 1
const val THEME_GREEN = 2

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
        init()

    }

    private fun init() {
        binding.apply {
            when (Parameters.getInstance().theme) {
                R.style.MyRedTheme -> chipTheme1.isChecked = true
                R.style.MyPinkTheme -> chipTheme2.isChecked = true
                R.style.MyGreenTheme -> chipTheme3.isChecked = true
            }
        }
        binding.chipTheme1.setOnClickListener { listener }
        binding.chipTheme2.setOnClickListener { listener }
        binding.chipTheme3.setOnClickListener { listener }
    }

    private val listener = View.OnClickListener {
        when(it.id){
            R.id.chip_theme_1 -> {
                Parameters.getInstance().theme = R.style.MyGreenTheme
                requireActivity().recreate()
                saveTheme(THEME_RED)
            }
            R.id.chip_theme_2 -> {
                Parameters.getInstance().theme = R.style.MyPinkTheme
                requireActivity().recreate()
                saveTheme(THEME_PINK)
            }
            R.id.chip_theme_3 -> {
                Parameters.getInstance().theme = R.style.MyRedTheme
                requireActivity().recreate()
                saveTheme(THEME_GREEN)
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
            SettingsFragment()
    }

    private fun saveTheme(key: Int) {
        activity?.let {
            it.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE).edit()
                .putInt(KEY_THEME, key).apply()
            it.recreate()
        }
    }
}