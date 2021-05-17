package com.weather.weatherify.ui.weatherforecast

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.weather.weatherify.R

class WeatherForecastFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherForecastFragment()
    }

    private lateinit var viewModel: WeatherForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weather_forecast_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherForecastViewModel::class.java)
        // TODO: Use the ViewModel
    }

}