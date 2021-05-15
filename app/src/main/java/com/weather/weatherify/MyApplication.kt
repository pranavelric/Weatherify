package com.weather.weatherify

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.weather.weatherify.utils.Constants
import com.weather.weatherify.utils.ThemeManager
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setAppTheme()

    }

    private fun setAppTheme() {

        val sp = getSharedPreferences(Constants.SHARED_PREFRENCE, 0)
//        if (sp.getBoolean(Constants.NIGHT_MODE_ENABLED, false)) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        }

        ThemeManager.applyTheme(
            sp.getString(Constants.THEME_PREF, Constants.FOLLOW_SYSTEM_MODE)
                ?: Constants.FOLLOW_SYSTEM_MODE
        )


    }

}