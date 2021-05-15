package com.weather.weatherify.mappers

import com.weather.weatherify.data.database.entities.DBWeather
import com.weather.weatherify.data.model.ResponseWeather

class WeatherMapperLocal : BaseMapper<DBWeather, ResponseWeather> {
    override fun transformToDomain(type: DBWeather): ResponseWeather =
        ResponseWeather(
            base = null,
            clouds = null,
            cod = null,
            coord = null,
            id = type.cityId,
            dt = null,
            name = type.cityName,
            sys = null,
            timezone = null,
            visibility = null,
            wind = type.wind,
            main = type.weatherConditions,
            weather = type.weatherDescription
        )

    override fun transformToDto(type: ResponseWeather): DBWeather =
        DBWeather(
            uId = type.id,
            cityName = type.name,
            wind = type.wind,
            weatherConditions = type.main,
            weatherDescription = type.weather,
            cityId = type.id
        )
}