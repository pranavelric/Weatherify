package com.weather.weatherify.utils

import androidx.appcompat.app.AppCompatDelegate
import com.weather.weatherify.utils.Constants.AUTO_BATTERY_MODE
import com.weather.weatherify.utils.Constants.DARK_MODE
import com.weather.weatherify.utils.Constants.FOLLOW_SYSTEM_MODE
import com.weather.weatherify.utils.Constants.LIGHT_MODE

object ThemeManager {

//    private const val LIGHT_MODE = "Light"
//    private const val DARK_MODE = "Dark"
//    private const val AUTO_BATTERY_MODE = "Auto-battery"
//    private const val FOLLOW_SYSTEM_MODE = "System"

    fun applyTheme(themePreference: String) {
        when (themePreference) {
            LIGHT_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            DARK_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            AUTO_BATTERY_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            FOLLOW_SYSTEM_MODE -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }



}