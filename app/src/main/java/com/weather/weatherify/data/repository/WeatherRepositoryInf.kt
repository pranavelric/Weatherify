package com.weather.weatherify.data.repository

import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.data.database.entities.DBWeather
import com.weather.weatherify.data.model.*

interface WeatherRepositoryInf {


    //weather
    suspend fun getWeatherByLocation(coord: Coord,refresh:Boolean): ResponseState<ResponseWeather?>
    suspend fun getWeatherByZipAndCountry(zipAndCountry: ZipAndCountryCode): ResponseState<ResponseWeather?>
    suspend fun getWeatherByCityName(name: String): ResponseState<ResponseWeather?>
    suspend fun getWeatherByCityId(id: Int): ResponseState<ResponseWeather?>

    //oneCall
    suspend fun getOneCallWeatherForecast(coord: Coord, units: String): ResponseState<ResponseOneCallWeatherForecast?>

    // forecast
    suspend fun getWeatherForecastByCityName(name: String): ResponseState<List<NetworkWeatherForecast>?>
    suspend fun getWeatherForecastByLocation(coord: Coord,refresh: Boolean): ResponseState<List<NetworkWeatherForecast>?>



    // local

    suspend fun getWeatherFromDatabase(): ResponseWeather?
    suspend fun saveWeatherToDatabase(weather: ResponseWeather?):Unit
    suspend fun deleteWeatherFromDatabase(weather: ResponseWeather?):Unit
    suspend fun deleteAllWeatherFromDatabase()
    suspend fun getAllWeatherFromDatabase():List<DBWeather>



}