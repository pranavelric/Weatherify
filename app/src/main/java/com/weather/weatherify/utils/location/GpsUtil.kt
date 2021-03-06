package com.weather.weatherify.utils.location

import android.app.Activity
import android.content.Context
import android.location.LocationManager
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.SettingsClient
import com.weather.weatherify.utils.Constants.GPS_REQUEST_CHECK_SETTINGS
import com.weather.weatherify.utils.toast_long


class GpsUtil(private val context: Context,private val activity: Activity) {
    private val settingsClient: SettingsClient = LocationServices.getSettingsClient(context)
    private val locationSettingsRequest: LocationSettingsRequest?
    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    init {
        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(LocationLiveData.locationRequest)
        locationSettingsRequest = builder.build()
        builder.setAlwaysShow(true)
    }

    fun turnGPSOn(OnGpsListener: OnGpsListener?) {
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGpsListener?.gpsStatus(true)
        } else {
            settingsClient
                .checkLocationSettings(locationSettingsRequest)
                .addOnSuccessListener(activity) {
                    OnGpsListener?.gpsStatus(true)
                }.addOnFailureListener {
                }.addOnFailureListener(activity) { exception ->

                    when ((exception as ApiException).statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            try {
                                val resApiException = exception as ResolvableApiException
                                resApiException.startResolutionForResult(activity, GPS_REQUEST_CHECK_SETTINGS)
                            } catch (sendIntentException: Exception) {
                                sendIntentException.printStackTrace()

                            }
                        }
                        LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {
                            val errorMessage =
                                "Location settings are inadequate, and cannot be " + "fixed here. Fix in Settings."

                            context.toast_long(errorMessage)

                        }
                    }
                }
        }
    }

    interface OnGpsListener {
        fun gpsStatus(isGPSEnabled: Boolean)
    }
}
