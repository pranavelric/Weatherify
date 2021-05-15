package com.weather.weatherify.data.remote

import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.data.model.*

interface WeatherRemoteDataSource {

    //weather
    suspend fun getWeatherByLocation(coord: Coord): ResponseState<ResponseWeather?>
    suspend fun getWeatherByZipAndCountry(zipAndCountry: ZipAndCountryCode): ResponseState<ResponseWeather?>
    suspend fun getWeatherByCityName(name: String): ResponseState<ResponseWeather?>
    suspend fun getWeatherByCityId(id: Int): ResponseState<ResponseWeather?>

    //oneCall
    suspend fun getOneCallWeatherForecast(coord: Coord, units: String): ResponseState<ResponseOneCallWeatherForecast?>

    // forecast
    suspend fun getWeatherForecastByCityName(name: String): ResponseState<List<NetworkWeatherForecast>?>
    suspend fun getWeatherForecastByLocation(coord: Coord): ResponseState<List<NetworkWeatherForecast>?>


}
