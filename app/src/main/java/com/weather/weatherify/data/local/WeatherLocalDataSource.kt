package com.weather.weatherify.data.local

import com.weather.weatherify.data.database.entities.DBWeather

interface WeatherLocalDataSource {

    suspend fun getWeather(): DBWeather?
    suspend fun saveWeather(weather: DBWeather)
    suspend fun deleteWeather(weather: DBWeather)
    suspend fun deleteAllWeahter()
    suspend fun getAllWeather(): List<DBWeather>


}