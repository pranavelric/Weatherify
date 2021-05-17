package com.weather.weatherify.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.github.pwittchen.weathericonview.WeatherIconView
import com.weather.weatherify.R
import java.text.DecimalFormat


fun checkAboveOreo(): Boolean {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.O
}

fun checkAboveKitkat(): Boolean {
    return Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT
}

fun checkAboveLollipop(): Boolean {
    return   Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP
}

fun Context.rateUs() {

    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("amzn://apps/android?p=$packageName")
            )
        )
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=$packageName")
            )
        )
    }

}


fun Context.createThemeDialog(): String {


    val builder = this.let { AlertDialog.Builder(it) }
    builder.setTitle("Choose Theme")

    var theme =Constants.FOLLOW_SYSTEM_MODE
    val themeArr  = arrayOf(Constants.FOLLOW_SYSTEM_MODE,Constants.DARK_MODE,Constants.LIGHT_MODE,Constants.AUTO_BATTERY_MODE)
    val animals = arrayOf( "Follow system theme","Night", "Light", "Auto battery-mode")
    val checkedItem = -1
    builder.setSingleChoiceItems(animals, checkedItem) { dialog, which ->
        theme = themeArr[which]
    }

    builder.setPositiveButton("OK") { dialog, which ->
        this.toast("Changing theme")
        ThemeManager.applyTheme(theme)

    }
    builder.setNegativeButton("Cancel", null)
    val dialog = builder.create()
    dialog.show()

    return theme
}




fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(
        lifecycleOwner,
        object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        }
    )
}





fun WeatherIconView.getIconResources(context: Context , condition: String?) {
    if (condition != null) {
        when {
            condition.contains("rain", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_rain))
            }
            condition.contains("snow", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_snow))
            }
            condition.contains("sun", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_sunny))
            }
            condition.contains("cloud", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_cloudy))
            }
            condition.contains("Clear", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_clear))
            }
            condition.contains("Overcast", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_sunny_overcast))
            }
            condition.contains("sleet", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_sleet_storm))
            }
            condition.contains("Mist", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_fog))
            }
            condition.contains("drizzle", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_raindrops))
            }
            condition.contains("thunderstorm", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_tstorms))
            }
            condition.contains("Thunder", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_thunderstorm))
            }
            condition.contains("Cloudy", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_cloudy))
            }
            condition.contains("Fog", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_fog))
            }
            condition.contains("Sunny", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_mostlysunny))
            }
            condition.contains("Blizzard", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_snow_wind))
            }
            condition.contains("Ice", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_wu_chancesnow))
            }
            condition.contains("ice", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_snow))
            }
            condition.contains("Rain", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_rain_wind))
            }
            condition.contains("wind", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_windy))
            }
            condition.contains("Wind", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_strong_wind))
            }
            condition.contains("storm", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_storm_warning))
            }
            condition.contains("Storm", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_thunderstorm))
            }
            condition.contains("thunder", ignoreCase = true) -> {
                this.setIconResource(context.getString(R.string.wi_day_snow_thunderstorm))
            }
            else -> {
                this.setIconResource(context.getString(R.string.wi_forecast_io_partly_cloudy_day))
            }
        }
    }
}


fun convertCelsiusToFahrenheit(celsius: Double): String {
    val f =  DecimalFormat().run {
        applyPattern(".##")
        parse(format(celsius.times(1.8).plus(32))).toDouble()
    }

    return "$f°F"

}








