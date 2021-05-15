package com.weather.weatherify.ui.splash

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.alarm.momentix.utils.CoroutinesHelper
import com.weather.weatherify.R
import com.weather.weatherify.data.model.Main
import com.weather.weatherify.databinding.FragmentSplashBinding
import com.weather.weatherify.ui.activity.MainActivity
import com.weather.weatherify.utils.*
import com.weather.weatherify.utils.location.GpsUtil
import com.weather.weatherify.utils.location.LocationLiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class SplashFragment : Fragment() {

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        private const val LOCATION_REQUEST_CODE = 121
    }


    private lateinit var binding: FragmentSplashBinding
    private var isGPSEnabled = false
    private val splashViewModel: SplashViewModel by lazy {
        ViewModelProvider(this).get(SplashViewModel::class.java)
    }


    override fun onStart() {
        super.onStart()
        invokeLocationAction()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setGps()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun goToMain() {
        CoroutinesHelper.delayWithMain(2000L) {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }

    }

    private fun invokeLocationAction() {
        when {
            allPermissionGranted() -> {
                (activity as MainActivity).locationLiveData.observe(
                    viewLifecycleOwner,
                    { location ->
                        if (location != null) {
                            goToMain()
                            //get weather here
                            Log.d("RRR", "invokeLocationAction:${location.lat} ${location.lon} ")
                        }

                    })
            }

            shouldShowRequestPermissionDialog() -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(getString(R.string.location_permission))
                    .setMessage(getString(R.string.access_location_message))
                    .setNegativeButton(
                        getString(R.string.no)
                    ) { _, _ -> requireActivity().finish() }
                    .setPositiveButton(
                        getString(R.string.ask_me)
                    ) { _, _ ->
                        requestPermissions(REQUIRED_PERMISSIONS, LOCATION_REQUEST_CODE)
                    }
                    .show()
            }

            !isGPSEnabled -> {
                binding.root.snackbar(getString(R.string.gps_required_message))
            }
            else -> {
                requestPermissions(REQUIRED_PERMISSIONS, LOCATION_REQUEST_CODE)
            }

        }


    }

    private fun allPermissionGranted() = Companion.REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    private fun shouldShowRequestPermissionDialog() = REQUIRED_PERMISSIONS.all {
        shouldShowRequestPermissionRationale(it)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            invokeLocationAction()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_REQUEST_CODE) {
            Log.d("RRR", "onActivityResult: ${requestCode}")
            when (resultCode) {
                Activity.RESULT_OK -> {

                    isGPSEnabled = true
                    invokeLocationAction()
                }
                Activity.RESULT_CANCELED -> {
                    context?.toast_long("Enable your GPS and restart!")
                }

            }
        }

    }


    private fun setGps() {
        GpsUtil(requireContext(), activity as MainActivity).turnGPSOn(object :
            GpsUtil.OnGpsListener {
            override fun gpsStatus(isGPSEnabled: Boolean) {
                this@SplashFragment.isGPSEnabled = isGPSEnabled
            }
        })
    }


}