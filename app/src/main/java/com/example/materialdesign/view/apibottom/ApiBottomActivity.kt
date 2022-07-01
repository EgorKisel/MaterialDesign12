package com.example.materialdesign.view.apibottom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialdesign.R
import com.example.materialdesign.databinding.ActivityApiBottomBinding
import com.example.materialdesign.view.api.EarthFragment
import com.example.materialdesign.view.api.MarsFragment
import com.example.materialdesign.view.api.SystemFragment
import com.google.android.material.badge.BadgeDrawable

class ApiBottomActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.recyclerView -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, EarthFragment()).addToBackStack("").commit()
                }
                R.id.action_bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, MarsFragment()).addToBackStack("").commit()
                }
                R.id.action_bottom_view_system -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SystemFragment()).addToBackStack("").commit()
                }
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.action_bottom_view_system
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.action_bottom_view_system)
        badge.number = 1000
        badge.maxCharacterCount = 5
        badge.badgeGravity = BadgeDrawable.BOTTOM_START
    }
}