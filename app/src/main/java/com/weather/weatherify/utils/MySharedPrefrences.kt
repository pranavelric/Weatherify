package com.weather.weatherify.utils

import android.content.Context
import android.content.SharedPreferences
import com.weather.weatherify.data.model.Coord
import com.weather.weatherify.utils.Constants.BACKGROUND_IMAGE
import com.weather.weatherify.utils.Constants.CITY_ID
import com.weather.weatherify.utils.Constants.CITY_LOCATION
import com.weather.weatherify.utils.Constants.NIGHT_MODE_ENABLED
import com.weather.weatherify.utils.Constants.NOT_FOUND
import com.weather.weatherify.utils.Constants.THEME_PREF
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MySharedPrefrences @Inject constructor(@ApplicationContext context: Context) {

    private val sp: SharedPreferences by lazy {
        context.getSharedPreferences(Constants.SHARED_PREFRENCE, 0)
    }
    private var editor = sp.edit()

    fun clearSession() {
        editor.clear()
        editor.commit()
    }


    fun setNightModeEnabled(nightMode: Boolean) {
        editor.putBoolean(NIGHT_MODE_ENABLED, nightMode)
        editor.commit()
    }

    fun getIsNightModeEnabled(): Boolean {
        return sp.getBoolean(NIGHT_MODE_ENABLED, false)
    }


    fun setThemePref(themePref: String) {
        editor.putString(THEME_PREF, themePref)
        editor.commit()
    }

    fun getThemePref(): String {
        return sp.getString(THEME_PREF, Constants.FOLLOW_SYSTEM_MODE)
            ?: Constants.FOLLOW_SYSTEM_MODE
    }


    fun setBackgroundImage(path: String) {
        editor.putString(BACKGROUND_IMAGE, path)
        editor.commit()
    }

    fun getBrackgroundImage(): String? {
        return sp.getString(BACKGROUND_IMAGE, NOT_FOUND)
    }


    fun saveCityId(cityId: Int) {
        editor.putInt(CITY_ID, cityId)
        editor.commit()
    }

    fun getCityId() = sp.getInt(CITY_ID, 0)

    fun saveLocation(location: Coord) {
        val lat = location.lat
        val lon = location.lon
        val loc = "$lat,$lon"
        editor.putString(CITY_LOCATION, loc)

    }

    fun getLocation(): Coord {
        val loc = sp.getString(CITY_LOCATION, null)
        val arr = loc?.split(",")
        val lat = arr?.get(0)?.toDouble()
        val lon = arr?.get(1)?.toDouble()
        return Coord(lat, lon)
    }


}