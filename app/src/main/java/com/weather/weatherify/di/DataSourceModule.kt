package com.weather.weatherify.di

import com.weather.weatherify.data.local.WeatherLocalDataSource
import com.weather.weatherify.data.local.WeatherLocalDataSourceImpl
import com.weather.weatherify.data.remote.WeatherRemoteDataSource
import com.weather.weatherify.data.remote.WeatherRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ApplicationComponent::class)
abstract  class DataSourceModule {

    @ActivityScoped
    @Binds
    abstract fun bindWeatherLocalDataSource(weatherLocalDataSourceImpl: WeatherLocalDataSourceImpl): WeatherLocalDataSource

    @ActivityScoped
    @Binds
    abstract fun bindWeatherRemoteDataSource(weatherRemoteDataSourceImpl: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource


}