package com.weather.weatherify.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.weather.weatherify.data.model.NetworkWeatherForecast
import com.weather.weatherify.databinding.WeatherItemBinding
import com.weather.weatherify.utils.getIconResources
import com.weather.weatherify.utils.getTimeFromMillis

class WeatherForecastAdapter() :
    ListAdapter<NetworkWeatherForecast, WeatherForecastAdapter.MyWeatherViewHolder>(
        WeatherForecastDiffCallBack()
    ) {

lateinit var context:Context

    inner class MyWeatherViewHolder(private val binding: WeatherItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, weatherForecast: NetworkWeatherForecast) {
            binding.weatherMain.text = weatherForecast.weather[0].main
            binding.weatherDescription.text = weatherForecast.weather.get(0).description
            binding.weatherIcon.getIconResources(context, weatherForecast.weather[0].main)
            binding.weatherTime.text = weatherForecast.dt_txt
            binding.cityTemp.text = weatherForecast.main?.temp.toString()+ "°C"
            binding.humidityText.text = weatherForecast.main?.humidity.toString()+"%"
            binding.pressureText.text = weatherForecast.main?.pressure.toString()+ "hPa"
            binding.windText.text = weatherForecast.wind?.speed.toString()+ "m/s"


        }

    }


    class WeatherForecastDiffCallBack : DiffUtil.ItemCallback<NetworkWeatherForecast>() {
        override fun areItemsTheSame(
            oldItem: NetworkWeatherForecast,
            newItem: NetworkWeatherForecast
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NetworkWeatherForecast,
            newItem: NetworkWeatherForecast
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWeatherViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = WeatherItemBinding.inflate(layoutInflater, parent, false)
        return MyWeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyWeatherViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    private var onItemClickListener: ((NetworkWeatherForecast) -> Unit)? = null
    fun setOnItemClickListener(listener: (NetworkWeatherForecast) -> Unit) {
        onItemClickListener = listener
    }




}