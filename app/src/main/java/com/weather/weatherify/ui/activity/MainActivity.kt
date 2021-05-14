package com.weather.weatherify.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.weather.weatherify.R
import com.weather.weatherify.databinding.ActivityMainBinding
import com.weather.weatherify.utils.MySharedPrefrences
import com.weather.weatherify.utils.setFullScreen
import com.weather.weatherify.utils.setFullScreenForNotch
import com.weather.weatherify.utils.setFullScreenWithBtmNav
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    override fun onStart() {
        super.onStart()
        //setFullScreenWithBtmNav()
        setFullScreen()
        setFullScreenForNotch()
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


}