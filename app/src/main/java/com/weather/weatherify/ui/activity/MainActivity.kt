package com.weather.weatherify.ui.activity

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.weather.weatherify.R
import com.weather.weatherify.databinding.ActivityMainBinding
import com.weather.weatherify.utils.*
import com.weather.weatherify.utils.location.GpsUtil
import com.weather.weatherify.utils.location.LocationLiveData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    val navController: NavController by lazy {
        findNavController(R.id.fragment)
    }

    @Inject
    lateinit var mySharedPrefrences: MySharedPrefrences
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var locationLiveData: LocationLiveData




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }


    override fun onStart() {
        super.onStart()

        setFullScreenForNotch()
        setFullScreenWithBtmNav()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }






}