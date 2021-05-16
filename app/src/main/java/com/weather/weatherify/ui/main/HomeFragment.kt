package com.weather.weatherify.ui.main

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.weather.weatherify.R
import com.weather.weatherify.databinding.FragmentHomeBinding
import com.weather.weatherify.ui.activity.MainActivity
import com.weather.weatherify.utils.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.toolbar.setOnMenuItemClickListener {
            onOptionsItemSelected(it)
        }
        setHasOptionsMenu(true)
        setSlidingBehaviour()
        setViews()
        setClickListeners()



        return binding.root
    }

    private fun setClickListeners() {

    }

    private fun setViews() {

        binding.background.getBackgroundImage(Uri.parse((activity as MainActivity).mySharedPrefrences.getBrackgroundImage()))



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
                        binding.fragmentListalarmsAddAlarm.show()
                    } else {
                        binding.tempView.alpha = (slideOffset)
                        binding.fragmentListalarmsAddAlarm.hide()
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


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
               findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
                return true
            }
//            R.id.action_rate -> {
//
//                activity?.rateUs()
//                return true
//            }
//
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