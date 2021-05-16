package com.weather.weatherify.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AlertDialog


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