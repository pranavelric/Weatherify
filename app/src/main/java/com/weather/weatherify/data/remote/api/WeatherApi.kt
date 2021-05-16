package com.weather.weatherify.data.remote.api

import com.weather.weatherify.data.model.ResponseWeather
import com.weather.weatherify.data.model.ResponseOneCallWeatherForecast
import com.weather.weatherify.data.model.ResponseWeatherForecast
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {


    @GET("forecast")
    suspend fun getWeatherForecastByCityName(
        @Query("q")
        q: String,
    ): Response<ResponseWeatherForecast>

    @GET("forecast")
    suspend fun getWeatherForecastByLocation(
        @Query("lat")
        latitude: String,
        @Query("lon")
        longitude: String
    ): Response<ResponseWeatherForecast>



    @GET("weather")
    suspend fun getWeatherByCityName(
        @Query("q")
        q: String,
    ): Response<ResponseWeather>

    @GET("weather")
    suspend fun getWeatherByZipAndContryCode(
        @Query("zip")
        zip: String,
    ): Response<ResponseWeather>

    @GET("weather")
    suspend fun getWeatherByLocation(
        @Query("lat")
        latitude: String,
        @Query("lon")
        longitude: String
    ): Response<ResponseWeather>

    @GET("weather")
    suspend fun getWeatherByCityID(
        @Query("id")
        query: String
    ): Response<ResponseWeather>



    @GET("onecall")
    suspend fun getWeatherForecast(
        @Query("lat")
        latitude: String,
        @Query("lon")
        longitude: String,
        @Query("units")
        units: String
    ): Response<ResponseOneCallWeatherForecast>

//onecall
    //  https://api.openweathermap.org/data/2.5/onecall?lat=73.8957&lon=29.3221&appid=f6c3ad76ffa170293b68f73828d72a9a&units=metric

//lat=73.8957, lon=29.3221
//    http://api.openweathermap.org/data/2.5/forecast?q=suratgarh&appid=f6c3ad76ffa170293b68f73828d72a9a&units=metric

}