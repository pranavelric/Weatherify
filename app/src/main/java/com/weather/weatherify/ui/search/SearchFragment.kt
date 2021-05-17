package com.weather.weatherify.ui.search

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.data.model.ResponseWeather
import com.weather.weatherify.databinding.SearchFragmentBinding
import com.weather.weatherify.ui.activity.MainActivity
import com.weather.weatherify.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment() : Fragment() {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }
    lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getData()
        observeData()
        setClickListeners()


    }

    private fun getData() {
        binding.background.getBackgroundImage(Uri.parse((activity as MainActivity).mySharedPrefrences.getBrackgroundImage()))
        binding.emptySearchLayout.visible()


    }

    private fun setClickListeners() {
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.searchview.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(!query.isNullOrBlank())
                query?.let { viewModel.getWeatherByCityName(it) }
                else{
                context?.toast("Please enter city name")
                }

            return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })



    }

    private fun observeData() {
with(viewModel){
    weatherData.observe(viewLifecycleOwner) { weather ->
        weather?.let { weather_state ->
            when (weather_state) {
                is ResponseState.Success -> {
                    binding.progressBar.gone()
                    binding.errorLay.root.gone()
                    setData(weather_state.data)

                }
                is ResponseState.Error -> {
                    binding.emptySearchLayout.gone()
                    binding.progressBar.gone()
                    weather_state.message?.let { binding.root.snackbar(it) }
                    if (weather_state.message == "No internet Connection") {
                        binding.errorLay.root.visible()
                    }

                }
                is ResponseState.Loading -> {
                    binding.progressBar.gone()
                }
            }


        }
    }



}
    }



    private fun setData(data: ResponseWeather?) {

        if(!data?.name.isNullOrBlank()){
            binding.emptySearchLayout.gone()
            binding.weatherInText.text = data?.name
        binding.dateText.text = viewModel.currentSystemTime()
        binding.weatherTemperature.text = getTemp(data?.main?.temp)
        binding.weatherMinMax.text =
            getTemp(data?.main?.temp_max) + "/" + getTemp(data?.main?.temp_min)
        binding.weatherMain.text = data?.weather?.get(0)?.description
        binding.humidityText.text = data?.main?.humidity.toString() + "%"
        binding.pressureText.text = data?.main?.pressure.toString() + "hPa"
        binding.windSpeedText.text = data?.wind?.speed.toString() + "m/s"
        context?.let {
            binding.weatherIcon.getIconResources(
                it,
                data?.weather?.get(0)?.description
            )
        }
        }
        else{
            binding.emptySearchLayout.visible()
        }

    }


    fun getTemp(temp: Double?): String {
        var mTemp: String = ""
        if ((activity as MainActivity).mySharedPrefrences.getUnitsOfMeasurement() == Constants.FAHRENHEIT)
            mTemp = temp?.let { convertCelsiusToFahrenheit(it) }.toString()
        else
            mTemp = temp?.toString() + "°C"
        return mTemp
    }


}