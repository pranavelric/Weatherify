package com.weather.weatherify.di

import com.weather.weatherify.adapters.WeatherForecastAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@InstallIn(ActivityComponent::class)
@Module
class MyAdapterModule {

    @Provides
    fun providesWeatherForecastAdapter(): WeatherForecastAdapter {
        return WeatherForecastAdapter()
    }
}