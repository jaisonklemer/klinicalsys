package com.klemer.klinicalsys.view.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.ActivityControlBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.utils.extensions.replaceFragment
import com.klemer.klinicalsys.view.fragments.DoctorDetailFragment
import com.klemer.klinicalsys.view.fragments.PatientDetailFragment
import com.klemer.klinicalsys.view.fragments.SpecialtyDetailFragment
import com.klemer.klinicalsys.view.fragments.SpecialtyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlActivity : BaseActivity() {

    private var type: Int? = null
    private var patient_id: Int? = null
    private var specialty_id: Int? = null
    private var doctor_id: Int? = null
    private lateinit var currentFragment: Fragment

    private lateinit var binding: ActivityControlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControlBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupActionBar()
        getActivityParams()

        replaceFragment(R.id.root_container_control, setupFragment(), false)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupFragment(): Fragment {
        val args = Bundle()
        when (type) {

            Types.PATIENT.type -> {
                setAppBarTitle("Edit Patient")
                currentFragment = PatientDetailFragment.newInstance()
                patient_id?.let { args.putInt("patient_id", it) }
            }
            Types.SPECIALTY.type -> {
                setAppBarTitle("Edit Specialty")
                currentFragment = SpecialtyDetailFragment.newInstance()
                specialty_id?.let { args.putInt("specialty_id", it) }
            }

            Types.DOCTOR.type -> {
                setAppBarTitle("Edit Doctor")
                currentFragment = DoctorDetailFragment.newInstance()
                doctor_id?.let { args.putInt("doctor_id", it) }
            }
        }
        currentFragment.arguments = args
        return currentFragment
    }

    private fun getActivityParams() {
        type = intent.getIntExtra("type", 1)
        patient_id = intent.getIntExtra("patient_id", 0)
        specialty_id = intent.getIntExtra("specialty_id", 0)
        doctor_id = intent.getIntExtra("doctor_id", 0)
    }

    private fun setAppBarTitle(title: String) {
        supportActionBar?.title = title
    }
}