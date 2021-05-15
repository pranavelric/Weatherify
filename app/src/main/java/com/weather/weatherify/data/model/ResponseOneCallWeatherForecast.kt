package com.weather.weatherify.data.model

import com.squareup.moshi.Json


data class ResponseOneCallWeatherForecast(
    @Json(name ="daily")
    val daily: List<Daily>?,
    @Json(name ="lat")
    val lat: Double?,
    @Json(name ="lon")
    val lon: Double?,
    @Json(name ="timezone")
    val timezone: String?,
    @Json(name ="timezone_offset")
    val timezoneOffset: Int?
)

data class Daily(
    @Json(name ="clouds")
    val clouds: Int?,
    @Json(name ="dew_point")
    val dewPoint: Double?,
    @Json(name ="dt")
    val dt: Int?,
    @Json(name ="feels_like")
    val feelsLike: FeelsLike?,
    @Json(name ="humidity")
    val humidity: Int?,
    @Json(name ="pop")
    val pop: Double?,
    @Json(name ="pressure")
    val pressure: Int?,
    @Json(name ="rain")
    val rain: Double?,
    @Json(name ="sunrise")
    val sunrise: Int?,
    @Json(name ="sunset")
    val sunset: Int?,
    @Json(name ="temp")
    val temp: Temp?,
    @Json(name ="uvi")
    val uvi: Double?,
    @Json(name ="weather")
    val weather: List<Weather>?,
    @Json(name ="wind_deg")
    val windDeg: Int?,
    @Json(name ="wind_speed")
    val windSpeed: Double?
)

data class FeelsLike(
    @Json(name ="day")
    val day: Double?,
    @Json(name ="eve")
    val eve: Double?,
    @Json(name ="morn")
    val morn: Double?,
    @Json(name ="night")
    val night: Double?
)

data class Temp(
    @Json(name ="day")
    val day: Double?,
    @Json(name ="eve")
    val eve: Double?,
    @Json(name ="max")
    val max: Double?,
    @Json(name ="min")
    val min: Double?,
    @Json(name ="morn")
    val morn: Double?,
    @Json(name ="night")
    val night: Double?
)

