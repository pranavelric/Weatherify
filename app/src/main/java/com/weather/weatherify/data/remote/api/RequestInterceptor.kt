package com.weather.weatherify.data.remote.api

import com.weather.weatherify.utils.Constants
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url
                .newBuilder()
                .addQueryParameter(Constants.API_KEY_QUERY, Constants.API_KEY_VALUE)
                .build()
            val request = chain.request().newBuilder().url(url).build()
            return chain.proceed(request)
        }
    }


