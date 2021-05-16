package com.weather.weatherify.ui.settings

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.weather.weatherify.databinding.FragmentSettingsBinding
import com.weather.weatherify.ui.activity.MainActivity
import com.weather.weatherify.utils.*


class SettingsFragment : Fragment() {


    private lateinit var binding: FragmentSettingsBinding


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
        binding.expandedImage.getBackgroundImage(Uri.parse((activity as MainActivity).mySharedPrefrences.getBrackgroundImage()))
        binding.themeSelected.text = (activity as MainActivity).mySharedPrefrences.getThemePref()
        binding.measurementUnitSelected.text =
            (activity as MainActivity).mySharedPrefrences.getUnitsOfMeasurement()

    }

    private fun setClickListeners() {

        binding.background.setOnClickListener {
            selectImage()
        }
        binding.theme.setOnClickListener { selectTheme() }
        binding.themeSelected.setOnClickListener { selectTheme() }
        binding.measurementUnit.setOnClickListener { selectUnitsSystem() }
        binding.measurementUnitSelected.setOnClickListener { selectUnitsSystem() }

    }

    private fun selectTheme() {
        //   val theme = context?.createThemeDialog()

        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Choose Theme")
        var theme: String = (activity as MainActivity).mySharedPrefrences.getThemePref()
        val themeArr = arrayOf(
            Constants.FOLLOW_SYSTEM_MODE,
            Constants.DARK_MODE,
            Constants.LIGHT_MODE,
            Constants.AUTO_BATTERY_MODE
        )
        val animals = arrayOf("Follow system", "Dark theme", "Light theme", "Auto battery")
        val checkedItem = -1
        builder?.setSingleChoiceItems(animals, checkedItem) { dialog, which ->
            theme = themeArr[which]
        }
        builder?.setPositiveButton("OK") { dialog, which ->
            context?.toast("Changing theme")
            ThemeManager.applyTheme(theme)
            binding.themeSelected.text = theme
            (activity as MainActivity).mySharedPrefrences.setThemePref(theme)
        }
        builder?.setNegativeButton("Cancel", null)
        val dialog = builder?.create()
        dialog?.show()


    }


    private fun selectUnitsSystem() {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setTitle("Units of Temperature")
        var uom: String = (activity as MainActivity).mySharedPrefrences.getUnitsOfMeasurement()
        val uomArr = arrayOf(Constants.CELSIUS, Constants.FAHRENHEIT)
        val animals = arrayOf("Celsius (°C)", "Fahrenheit (°F)")
        val checkedItem = -1
        builder?.setSingleChoiceItems(animals, checkedItem) { dialog, which ->
            uom = uomArr[which]
        }
        builder?.setPositiveButton("OK") { dialog, which ->
            context?.toast("Changing Temprerature unit")
            (activity as MainActivity).mySharedPrefrences.setUnitsOfMeasurement(uom)
            binding.measurementUnitSelected.text = uom
        }
        builder?.setNegativeButton("Cancel", null)
        val dialog = builder?.create()
        dialog?.show()
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
            binding.expandedImage.getBackgroundImage(uri)
        }
    }


}