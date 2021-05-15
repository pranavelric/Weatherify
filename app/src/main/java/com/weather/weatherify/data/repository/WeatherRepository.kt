package com.weather.weatherify.data.repository

import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.data.database.entities.DBWeather
import com.weather.weatherify.data.local.WeatherLocalDataSource
import com.weather.weatherify.data.model.*
import com.weather.weatherify.data.remote.WeatherRemoteDataSource
import com.weather.weatherify.data.remote.api.WeatherApi
import com.weather.weatherify.mappers.WeatherMapperLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val remoteDataSource: WeatherRemoteDataSource,
    private val localDataSource: WeatherLocalDataSource
) : WeatherRepositoryInf {
    //weather
    override suspend fun getWeatherByLocation(
        coord: Coord,
        refresh: Boolean
    ): ResponseState<ResponseWeather?> = withContext(Dispatchers.IO) {
        if (refresh) {
            when (val response = remoteDataSource.getWeatherByLocation(coord)) {
                is ResponseState.Success -> {
                    ResponseState.Success(response.data)
                }
                is ResponseState.Error -> {
                    if (response.message != null)
                        ResponseState.Error(response.message)
                    else
                        ResponseState.Error("Some error occured!")
                }
                else -> ResponseState.Loading()
            }
        } else {

            val mapper = WeatherMapperLocal()
            val forecast = localDataSource.getWeather()
            if (forecast != null) {
                ResponseState.Success(mapper.transformToDomain(forecast))
            } else {
                ResponseState.Success(null)
            }

        }

    }

    override suspend fun getWeatherByZipAndCountry(zipAndCountry: ZipAndCountryCode): ResponseState<ResponseWeather?> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getWeatherByZipAndCountry(zipAndCountry)
        }

    override suspend fun getWeatherByCityName(name: String): ResponseState<ResponseWeather?> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getWeatherByCityName(name)
        }

    override suspend fun getWeatherByCityId(id: Int): ResponseState<ResponseWeather?> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getWeatherByCityId(id)
        }

    //oneCall
    override suspend fun getOneCallWeatherForecast(
        coord: Coord,
        units: String,

        ): ResponseState<ResponseOneCallWeatherForecast?> = withContext(Dispatchers.IO) {

        remoteDataSource.getOneCallWeatherForecast(coord, units)
    }

    // forecast
    override suspend fun getWeatherForecastByCityName(name: String): ResponseState<List<NetworkWeatherForecast>?> =
        withContext(Dispatchers.IO) {
            remoteDataSource.getWeatherForecastByCityName(name)
        }

    override suspend fun getWeatherForecastByLocation(
        coord: Coord,
        refresh: Boolean
    ): ResponseState<List<NetworkWeatherForecast>?> = withContext(Dispatchers.IO) {
        if (refresh) {
            remoteDataSource.getWeatherForecastByLocation(coord)
        } else {
            remoteDataSource.getWeatherForecastByLocation(coord)
        }
    }

    //local
    override suspend fun getWeatherFromDatabase(): ResponseWeather?  = withContext(Dispatchers.IO) {
        val mapper = WeatherMapperLocal()
        localDataSource.getWeather()?.let { mapper.transformToDomain(it) }

    }

    override suspend fun saveWeatherToDatabase(weather: ResponseWeather?):Unit = withContext(Dispatchers.IO) {
        val mapper = WeatherMapperLocal()
        weather?.let { mapper.transformToDto(it) }?.let { localDataSource.saveWeather(it) }
    }

    override suspend fun deleteWeatherFromDatabase(weather: ResponseWeather?):Unit = withContext(Dispatchers.IO) {
        val mapper = WeatherMapperLocal()
        weather?.let { mapper.transformToDto(it) }?.let { localDataSource.deleteWeather(it) }
    }

    override suspend fun deleteAllWeatherFromDatabase() = withContext(Dispatchers.IO) {
        localDataSource.deleteAllWeahter()
    }

    override suspend fun getAllWeatherFromDatabase(): List<DBWeather> = withContext(Dispatchers.IO) {
        localDataSource.getAllWeather()
    }

}