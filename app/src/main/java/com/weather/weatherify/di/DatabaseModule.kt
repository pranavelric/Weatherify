package com.weather.weatherify.di

import android.content.Context
import androidx.room.Room
import com.weather.weatherify.data.database.WeatherDao
import com.weather.weatherify.data.database.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun providesWeatherDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java,
            WeatherDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }


    @Singleton
    @Provides
    fun providesRecordDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.dao()
    }


}