package com.weather.weatherify.utils.typeConverters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.weather.weatherify.data.model.Weather
import java.lang.reflect.ParameterizedType

class MyTypeConverter {


    private val moshi = Moshi.Builder().build()
    private val listMyData: ParameterizedType =
        Types.newParameterizedType(List::class.java, Weather::class.java)
    private val jsonAdapter: JsonAdapter<List<Weather>> = moshi.adapter(listMyData)

    @TypeConverter
    fun fromWeatherListToString(value: List<Weather>) = jsonAdapter.toJson(value)

    @TypeConverter
    fun fromStringToWeatherList(value: String) = jsonAdapter.fromJson(value)


}