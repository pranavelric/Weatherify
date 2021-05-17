package com.weather.weatherify.ui.weatherforecast

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.movies.animefied.utils.ResponseState
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import com.weather.weatherify.R
import com.weather.weatherify.adapters.WeatherForecastAdapter
import com.weather.weatherify.data.model.NetworkWeatherForecast
import com.weather.weatherify.data.model.ResponseWeather
import com.weather.weatherify.databinding.WeatherForecastFragmentBinding
import com.weather.weatherify.ui.activity.MainActivity
import com.weather.weatherify.utils.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class WeatherForecastFragment : Fragment() {


    private val viewModel: WeatherForecastViewModel by lazy {
        ViewModelProvider(this).get(WeatherForecastViewModel::class.java)
    }

    lateinit var binding: WeatherForecastFragmentBinding

    @Inject
    lateinit var weatherForecastAdapter: WeatherForecastAdapter


    lateinit var mList:List<NetworkWeatherForecast>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WeatherForecastFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setViews()
        getData()
        setupCalendar()
        setOnClickListeners()
        observeData()

    }

    private fun setOnClickListeners() {
        binding.forecastSwipeRefresh.setOnRefreshListener {
            binding.recProg.visible()
            initiateRefresh()
            binding.forecastSwipeRefresh.isRefreshing = false
        }

    }

    private fun getData() {
        initiateRefresh()
    }

    private fun observeData(){
        with(viewModel){
            weatherForecast.observe(viewLifecycleOwner) { forecastState ->
                when (forecastState) {
                    is ResponseState.Success -> {
                        binding.recProg.gone()
                        UnhideViewFields()
                        forecastState.data?.let { dataList ->
                            weatherForecastAdapter.submitList(dataList)
                            if (dataList.isEmpty()) {
                                binding.emptyLayout.root.visible()
                            } else {
                                binding.emptyLayout.root.gone()
                            }

                            mList = dataList

                        }

                    }
                    is ResponseState.Error -> {
                        binding.recProg.gone()
                        forecastState.message?.let {
                            binding.root.snackbar(it)
                            hideViewFields(it)
                        }
                    }
                    is ResponseState.Loading -> {
                        binding.recProg.visible()
                    }
                }

            }
        }
    }

    private fun setViews() {

        binding.background.getBackgroundImage(Uri.parse((activity as MainActivity).mySharedPrefrences.getBrackgroundImage()))
        binding.forecastRecyclerview.apply {
            adapter = weatherForecastAdapter
        }
    }


    private fun setupCalendar() {

        binding.calendarView.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onClickListener() {
            }

            override fun onDataUpdate() {
            }

            override fun onDayChanged() {
            }

            override fun onDaySelect() {
                val selectedDay = binding.calendarView.selectedDay
                if (selectedDay != null) {
                    val checkerDay = selectedDay.day
                    val checkerMonth = selectedDay.month
                    val checkerYear = selectedDay.year

                    val list = mList
                    val filteredList = list?.filter { weatherForecast ->
                        val format = SimpleDateFormat("yyyy-M-dd HH:mm:ss", Locale.US)
                        val formattedDate = format.parse(weatherForecast.dt_txt)
                        val weatherForecastDay = formattedDate?.date
                        val weatherForecastMonth = formattedDate?.month
                        val weatherForecastYear = formattedDate?.year
                        // This checks if the selected day, month and year are equal. The year requires an addition of 1900 to get the correct year.
                        weatherForecastDay == checkerDay && weatherForecastMonth == checkerMonth && weatherForecastYear?.plus(
                            1900
                        ) == checkerYear
                    }

                    weatherForecastAdapter.submitList(filteredList)
                    weatherForecastAdapter.currentList
                    binding.emptyLayout.root.isVisible = filteredList!!.isEmpty()
                }
            }

            override fun onItemClick(v: View) {
            }

            override fun onMonthChange() {
            }

            override fun onWeekChange(position: Int) {
            }
        })
    }
    fun hideViewFields(error: String?) {

        if (error == "No internet Connection") {
            binding.errorLayout.root.visible()
            binding.emptyLayout.root.gone()
        } else {
            binding.emptyLayout.root.visible()
            binding.errorLayout.root.gone()
        }
        binding.forecastRecyclerview.gone()


    }

    fun UnhideViewFields() {
        binding.forecastRecyclerview.visible()

        binding.errorLayout.root.gone()
        binding.emptyLayout.root.gone()
    }



    private fun initiateRefresh() {
        (activity as MainActivity).locationLiveData.observeOnce(viewLifecycleOwner, { location ->
            if (location != null) {
                viewModel.getWeatherForecastByLocation(location)
            } else {
                binding.root.snackbar("Error!, cannot fetch your location")
            }
        })
    }

}