package com.weather.weatherify.mappers

interface BaseMapper<A, B> {

    fun transformToDomain(type: A): B

    fun transformToDto(type: B): A

}