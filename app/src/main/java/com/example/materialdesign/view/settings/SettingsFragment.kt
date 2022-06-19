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
        binding.chipTheme1.setOnClickListener {
            Parameters.getInstance().theme = R.style.MyGreenTheme
            requireActivity().recreate()
           // putTheme(R.style.MyGreenTheme)
        }
        binding.chipTheme2.setOnClickListener {
            Parameters.getInstance().theme = R.style.MyPinkTheme
            requireActivity().recreate()
           // putTheme(R.style.MyPinkTheme)
        }
        binding.chipTheme3.setOnClickListener {
            Parameters.getInstance().theme = R.style.MyRedTheme
            requireActivity().recreate()
           // putTheme(R.style.MyRedTheme)
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

    private fun putTheme(key: Int) {
        activity?.let {
            it.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE).edit()
                .putInt(KEY_THEME, key).apply()
            it.recreate()
        }
    }
    private fun getNumTheme(): Int {
        return activity?.let {
            it.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE).getInt(KEY_THEME, 0)
        } ?: 0
    }
}