package com.klemer.klinicalsys.view.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.ActivityMainBinding
import com.klemer.klinicalsys.utils.extensions.replaceFragment
import com.klemer.klinicalsys.view.fragments.AppointmentsFragment
import com.klemer.klinicalsys.view.fragments.DoctorsFragment
import com.klemer.klinicalsys.view.fragments.PatientsFragment
import com.klemer.klinicalsys.view.fragments.SpecialtyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setAppBarTitle("KlinicalSys: Patients")
        replaceFragment(R.id.root_container, PatientsFragment.newInstance(), false)

        setButtonsClick()
    }


    private fun setButtonsClick() {
        binding.bottomApp.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.btnPatients -> {
                    setAppBarTitle("KlinicalSys: Patients")
                    replaceFragment(
                        R.id.root_container,
                        PatientsFragment.newInstance(),
                        false
                    )
                }
                R.id.btnDoctors -> {
                    setAppBarTitle("KlinicalSys: Doctors")
                    replaceFragment(
                        R.id.root_container,
                        DoctorsFragment.newInstance(),
                        false
                    )
                }
                R.id.btnAppointments -> {
                    setAppBarTitle("KlinicalSys: Appointments")
                    replaceFragment(
                        R.id.root_container,
                        AppointmentsFragment.newInstance(),
                        false
                    )
                }

                R.id.btnSpecialty -> {
                    setAppBarTitle("KlinicalSys: Specialties")
                    replaceFragment(
                        R.id.root_container,
                        SpecialtyFragment.newInstance(),
                        false
                    )
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    private fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }
}