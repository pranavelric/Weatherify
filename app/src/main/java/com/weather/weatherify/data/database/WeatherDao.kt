package com.weather.weatherify.data.database

import androidx.room.*
import com.weather.weatherify.data.database.entities.DBWeather


@Dao
interface WeatherDao {

    // convert all returning to livedata

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(vararg dbWeather: DBWeather)


    @Query("SELECT * FROM weather_table ORDER BY unique_id DESC LIMIT 1")
    suspend fun getWeather(): DBWeather

    @Query("SELECT * FROM weather_table ORDER BY unique_id DESC")
    suspend fun getAllWeather(): List<DBWeather>

    @Query("DELETE FROM weather_table")
    suspend fun deleteAllWeather()

    @Delete
    suspend fun deleteWeather(weather:DBWeather)



}