package com.weather.weatherify.data.repository

import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.data.local.WeatherLocalDataSource
import com.weather.weatherify.data.model.*
import com.weather.weatherify.data.remote.WeatherRemoteDataSource
import com.weather.weatherify.data.remote.api.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val remoteDataSource: WeatherRemoteDataSource,private val localDataSource: WeatherLocalDataSource):WeatherRepositoryInf {
   //weather
    override suspend fun getWeatherByLocation(coord: Coord,refresh:Boolean): ResponseState<ResponseWeather?> = withContext(Dispatchers.IO){
        
   }

    override suspend fun getWeatherByZipAndCountry(zipAndCountry: ZipAndCountryCode): ResponseState<ResponseWeather?> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherByCityName(name: String): ResponseState<ResponseWeather?> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherByCityId(id: Int): ResponseState<ResponseWeather?> {
        TODO("Not yet implemented")
    }
    //oneCall
    override suspend fun getOneCallWeatherForecast(
        coord: Coord,
        units: String,

    ): ResponseState<ResponseOneCallWeatherForecast?> {
        TODO("Not yet implemented")
    }
    // forecast
    override suspend fun getWeatherForecastByCityName(name: String): ResponseState<List<NetworkWeatherForecast>?> {
        TODO("Not yet implemented")
    }

    override suspend fun getWeatherForecastByLocation(coord: Coord,refresh:Boolean): ResponseState<List<NetworkWeatherForecast>?> {
        TODO("Not yet implemented")
    }

    //local
    override suspend fun getWeatherFromDatabase(): ResponseWeather {
        TODO("Not yet implemented")
    }

    override suspend fun saveWeatherToDatabase(weather: ResponseWeather) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteWeatherFromDatabase(weather: ResponseWeather) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllWeatherFromDatabase() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllWeatherFromDatabase(): List<NetworkWeatherForecast>? {
        TODO("Not yet implemented")
    }

}