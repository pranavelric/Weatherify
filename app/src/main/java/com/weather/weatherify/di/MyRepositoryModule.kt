package com.weather.weatherify.di

import com.weather.weatherify.data.repository.WeatherRepository
import com.weather.weatherify.data.repository.WeatherRepositoryInf
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class MyRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsWeatherRepository(weatherRepository: WeatherRepository):WeatherRepositoryInf

}