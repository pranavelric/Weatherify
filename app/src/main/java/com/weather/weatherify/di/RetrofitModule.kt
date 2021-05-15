package com.weather.weatherify.di

import com.weather.weatherify.data.remote.api.RequestInterceptor
import com.weather.weatherify.data.remote.api.WeatherApi
import com.weather.weatherify.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(requestInterceptor: RequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder().connectTimeout(10000L, TimeUnit.MILLISECONDS)
            .addInterceptor(requestInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
    }

    @Singleton
    @Provides
    fun providesWeatherService(retrofit: Retrofit.Builder): WeatherApi {
        return retrofit.build().create(WeatherApi::class.java)
    }


}