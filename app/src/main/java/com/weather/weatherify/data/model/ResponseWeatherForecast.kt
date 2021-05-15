package com.weather.weatherify.data.model

import com.squareup.moshi.Json

data class ResponseWeatherForecast(

    @Json(name ="cod")
    val cod:String?,
    @Json(name ="list")
    val daily: List<NetworkWeatherForecast>?,
    @Json(name="city")
    val city:City?

)

data class NetworkWeatherForecast(


    @Json(name = "clouds")
    val clouds: Clouds?,

    @Json(name = "dt")
    val dt: Long?,

    @Json(name = "main")
    val main: Main?,

    @Json(name = "dt_txt")
    val dateText: String?,

    @Json(name = "sys")
    val sys: SysPod?,

    @Json(name = "visibility")
    val visibility: Int?,
    @Json(name = "weather")
    val weather: List<Weather>,
    @Json(name = "wind")
    val wind: Wind?

)

data class SysPod(
    @Json(name="pod")
    val pod:String?

)