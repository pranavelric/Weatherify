package com.weather.weatherify.di

import com.weather.weatherify.data.local.WeatherLocalDataSource
import com.weather.weatherify.data.remote.WeatherRemoteDataSource
import com.weather.weatherify.data.remote.api.WeatherApi
import com.weather.weatherify.data.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providesWeatherRepository(weatherLocalDataSource: WeatherLocalDataSource,weatherRemoteDataSource: WeatherRemoteDataSource): WeatherRepository {
        return WeatherRepository(weatherRemoteDataSource,weatherLocalDataSource)
    }
}