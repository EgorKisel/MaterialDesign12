package com.example.materialdesign

import android.app.Application
import android.content.Context
import android.media.MediaCodec.MetricsConstants.MODE
import androidx.appcompat.app.AppCompatDelegate
import com.example.materialdesign.utils.Parameters
import com.example.materialdesign.view.settings.KEY_THEME

class MaterialApp: Application() {
    override fun onCreate() {
        super.onCreate()

        Parameters.getInstance().apply {
            theme = getPreferencesTheme()
            mode = getPreferencesMode()
        }
    }
    private fun getPreferencesTheme(): Int{
        return applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE).getInt(KEY_THEME, R.style.MyRedTheme)
    }
    private fun getPreferencesMode(): Int{
        return applicationContext.getSharedPreferences("theme", Context.MODE_PRIVATE).getInt(MODE, AppCompatDelegate.MODE_NIGHT_NO)
    }
}