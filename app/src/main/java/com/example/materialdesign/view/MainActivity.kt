package com.example.materialdesign.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.materialdesign.R
import com.example.materialdesign.utils.Parameters
import com.example.materialdesign.view.pictureoftheday.PictureOfTheDayFragment
import com.example.materialdesign.view.settings.KEY_SETTINGS
import com.example.materialdesign.view.settings.KEY_THEME

const val KEY_THEME_TEAL = 0
const val KEY_THEME_GREEN = 1
const val KEY_THEME_PINK = 2
const val KEY_THEME_RED = 3

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(Parameters.getInstance().theme)
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commit()
        }
    }
    private fun getThemePreference(): Int {
        return when (getSharedPreferences(KEY_SETTINGS, MODE_PRIVATE).getInt(
            KEY_THEME,
            KEY_THEME_TEAL
        )) {
            KEY_THEME_RED -> R.style.MyRedTheme
            KEY_THEME_PINK -> R.style.MyPinkTheme
            KEY_THEME_GREEN -> R.style.MyGreenTheme
            else -> R.style.Theme_MaterialDesign
        }
    }
}