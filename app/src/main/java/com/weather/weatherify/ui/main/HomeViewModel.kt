package com.weather.weatherify.ui.main

import android.annotation.SuppressLint
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.data.model.Coord
import com.weather.weatherify.data.model.ResponseWeather
import com.weather.weatherify.data.repository.WeatherRepository
import com.weather.weatherify.utils.NetworkHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel @ViewModelInject constructor(val repository: WeatherRepository,val networkHelper: NetworkHelper) : ViewModel() {


    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading


    private val _weatherData: MutableLiveData<ResponseState<ResponseWeather?>> = MutableLiveData()
    val weatherData: LiveData<ResponseState<ResponseWeather?>> get() = _weatherData


    fun getWeatherByLocation(coord: Coord) = viewModelScope.launch {
        safeGetWeatherByLocation(coord)
    }

    private suspend fun safeGetWeatherByLocation(coord: Coord) {

        withContext(Dispatchers.IO){
            _weatherData.postValue(ResponseState.Loading())
            if(networkHelper.isNetworkConnected()){
                val response = repository.getWeatherByLocation(coord=coord, true)
                _weatherData.postValue(response)
            }
            else{
                _weatherData.postValue(ResponseState.Error("No internet Connection"))
            }
        }
    }




    @SuppressLint("SimpleDateFormat")
    fun currentSystemTime(): String {
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val dateFormat = SimpleDateFormat("EEEE MMM d, hh:mm aaa")
        return dateFormat.format(date)
    }


}