package com.example.materialdesign.view

import android.content.Context
import android.media.MediaCodec.MetricsConstants.MODE
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.materialdesign.R
import com.example.materialdesign.utils.Parameters
import com.example.materialdesign.view.pictureoftheday.PictureOfTheDayFragment
import com.example.materialdesign.view.settings.KEY_THEME

const val KEY_SP ="KEY_SP"
const val KEY_CURRENT_THEME ="KEY_CURRENT_THEME"
const val THEME_RED = 0
const val THEME_PINK = 1
const val THEME_GREEN = 2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Parameters.getInstance().apply {
            theme = getPreferencesTheme()
            mode = getPreferencesMode()
        }
        setTheme(getRealStyle(getCurrentTheme()))
        setContentView(R.layout.activity_main)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commit()
        }
    }
    private fun getPreferencesTheme(): Int{
        return applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE).getInt(KEY_THEME, R.style.MyRedTheme)
    }
    private fun getPreferencesMode(): Int{
        return applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE).getInt(MODE, AppCompatDelegate.MODE_NIGHT_NO)
    }

    private fun getRealStyle(currentTheme: Int): Int {
        return when (currentTheme) {
            THEME_RED -> R.style.MyRedTheme
            THEME_PINK -> R.style.MyPinkTheme
            THEME_GREEN -> R.style.MyGreenTheme
            else -> R.style.MyRedTheme
        }
    }
    private fun getCurrentTheme(): Int {
        val sharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return sharedPreferences.getInt(KEY_CURRENT_THEME, R.style.MyRedTheme)
    }
}