package com.weather.weatherify.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.movies.animefied.utils.ResponseState
import com.weather.weatherify.R
import com.weather.weatherify.adapters.WeatherForecastAdapter
import com.weather.weatherify.data.model.ResponseWeather
import com.weather.weatherify.databinding.FragmentHomeBinding
import com.weather.weatherify.ui.activity.MainActivity
import com.weather.weatherify.ui.search.SearchFragment
import com.weather.weatherify.utils.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    @Inject
    lateinit var weatherForecastAdapter: WeatherForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.toolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSlidingBehaviour()
        setViews()
        getData()
        setClickListeners()
        observeViewModel()


    }


    private fun getData() {
        binding.loadingLay.loadingLayout.visible()
        initiateRefresh()
    }

    private fun setClickListeners() {
        binding.swipeRefreshId.setOnRefreshListener {
            binding.loadingLay.loadingLayout.visible()
            initiateRefresh()
            binding.swipeRefreshId.isRefreshing = false
        }

        binding.weatherForecast.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_weatherForecastFragment)
        }
        binding.searchFab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
        }
    }

    private fun observeViewModel() {
        with(homeViewModel) {
            weatherData.observe(viewLifecycleOwner) { weather ->
                weather?.let { weather_state ->
                    when (weather_state) {
                        is ResponseState.Success -> {
                            binding.errorLayout.root.gone()
                            binding.loadingLay.loadingLayout.gone()
                            setData(weather_state.data)

                        }
                        is ResponseState.Error -> {
                            binding.loadingLay.loadingLayout.gone()
                            weather_state.message?.let { binding.root.snackbar(it) }

                            if (weather_state.message == "No internet Connection") {
                                binding.errorLayout.root.visible()
                            }

                        }
                        is ResponseState.Loading -> {
                            binding.loadingLay.loadingLayout.visible()
                        }
                    }


                }
            }


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

    private fun setData(data: ResponseWeather?) {

        binding.weatherInText.text = data?.name
        binding.dateText.text = homeViewModel.currentSystemTime()
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


    private fun initiateRefresh() {
        (activity as MainActivity).locationLiveData.observeOnce(viewLifecycleOwner, { location ->
            if (location != null) {
                homeViewModel.getWeatherByLocation(location)
                homeViewModel.getWeatherForecastByLocation(location)
            } else {
                binding.root.snackbar("Error!, cannot fetch your location")
            }
        })
    }


    private fun setViews() {

        binding.background.getBackgroundImage(Uri.parse((activity as MainActivity).mySharedPrefrences.getBrackgroundImage()))
        binding.forecastRecyclerview.apply {
            adapter = weatherForecastAdapter
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

    private fun setSlidingBehaviour() {
        val behavior = BottomSheetBehavior.from(binding.bottomSheet)
        if (checkAboveKitkat()) {

            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        binding.bottomSheet.setPadding(0, 0, 0, 0)

                    } else if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        activity?.getStatusBarHeight()?.let { bottomSheet.setPadding(0, it, 0, 0) }
                    }

                    if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {

                    if (slideOffset < 0.5F) {
                        binding.tempView.alpha = 0.5F
                        binding.searchFab.show()
                    } else {
                        binding.tempView.alpha = (slideOffset)
                        binding.searchFab.hide()
                    }
                    bottomSheet.setPadding(
                        0,
                        (slideOffset * activity?.getStatusBarHeight()!!).toInt(),
                        0,
                        0
                    )
                }
            })
        }

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
        binding.homeImg.gone()

    }

    fun UnhideViewFields() {
        binding.forecastRecyclerview.visible()
        binding.homeImg.visible()
        binding.errorLayout.root.gone()
        binding.emptyLayout.root.gone()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                return true
            }
            R.id.action_forecast -> {
                findNavController().navigate(R.id.action_homeFragment_to_weatherForecastFragment)
                return true
            }

            R.id.action_share -> {
                activity?.share("Playstore link", "text")
                return true
            }
            else -> {
                return false
            }
        }
    }


    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setFullScreenWithBtmNav()
        (activity as MainActivity).setFullScreenForNotch()
    }


}