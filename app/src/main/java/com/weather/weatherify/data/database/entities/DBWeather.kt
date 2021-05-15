package com.weather.weatherify.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.weather.weatherify.data.model.Main
import com.weather.weatherify.data.model.Weather
import com.weather.weatherify.data.model.Wind


@Entity(tableName = "weather_table")
data class DBWeather (
    @ColumnInfo(name = "unique_id")
    @PrimaryKey(autoGenerate = true)
    val uId:Int = 0,
    @ColumnInfo(name = "city_id")
    val cityId:Int,
    @ColumnInfo(name="city_name")
    val cityName:String?,
    @Embedded
    val wind: Wind?,
    @ColumnInfo(name="weather_details")
    val weatherDescription:List<Weather>,
    @Embedded
    val weatherConditions: Main?





    )