package com.weather.weatherify.ui.settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import com.weather.weatherify.R
import com.weather.weatherify.databinding.FragmentSettingsBinding
import com.weather.weatherify.ui.activity.MainActivity


class SettingsFragment : Fragment() {


    private lateinit var binding:FragmentSettingsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater)

        setClickListeners()
        setData()


        return binding.root
    }

    private fun setData() {

    }

    private fun setClickListeners() {

    }

    private fun selectImage() {

        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(intent, 9998)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 9998) {

            val uri: Uri = data?.data ?: return

            (activity as MainActivity).mySharedPrefrences.setBackgroundImage(uri.toString())


      //      binding.expandedImage.getBackgroundImage(uri)

        }
    }



    private fun setNightMode(checked: Boolean) {
        if (checked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            (activity as MainActivity).mySharedPrefrences.setNightModeEnabled(true)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            (activity as MainActivity).mySharedPrefrences.setNightModeEnabled(false)
        }
    }




}