package com.weather.weatherify.data.local

import com.weather.weatherify.data.database.WeatherDao
import com.weather.weatherify.data.database.entities.DBWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherLocalDataSourceImpl @Inject constructor(private val weatherDao: WeatherDao) :
    WeatherLocalDataSource {

    override suspend fun getWeather(): DBWeather? = withContext(Dispatchers.IO) {
        return@withContext weatherDao.getWeather()
    }


    override suspend fun saveWeather(weather: DBWeather) = withContext(Dispatchers.IO) {
        weatherDao.insertWeather(weather)
    }

    override suspend fun deleteWeather(weather: DBWeather) = withContext(Dispatchers.IO) {
        weatherDao.deleteWeather(weather)
    }

    override suspend fun deleteAllWeahter() = withContext(Dispatchers.IO) {
        weatherDao.deleteAllWeather()
    }

    override suspend fun getAllWeather(): List<DBWeather> = withContext(Dispatchers.IO) {
        weatherDao.getAllWeather()
    }
}