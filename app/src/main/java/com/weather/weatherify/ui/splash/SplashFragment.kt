package com.weather.weatherify.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.alarm.momentix.utils.CoroutinesHelper
import com.weather.weatherify.R
import com.weather.weatherify.databinding.FragmentSplashBinding
import com.weather.weatherify.ui.activity.MainActivity
import com.weather.weatherify.utils.setFullScreen
import com.weather.weatherify.utils.setFullScreenForNotch
import com.weather.weatherify.utils.setFullScreenWithBtmNav


class SplashFragment : Fragment() {


    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        goToMain()

        return binding.root
    }

    private fun goToMain() {
        CoroutinesHelper.delayWithMain(2000L){
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }

    }



}