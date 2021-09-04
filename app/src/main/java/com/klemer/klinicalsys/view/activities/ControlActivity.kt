package com.klemer.klinicalsys.view.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.ActivityControlBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.utils.extensions.replaceFragment
import com.klemer.klinicalsys.view.fragments.PatientDetailFragment
import com.klemer.klinicalsys.view.fragments.SpecialtyDetailFragment
import com.klemer.klinicalsys.view.fragments.SpecialtyFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlActivity : BaseActivity() {

    private var type: Int? = null
    private var patient_id: Int? = null
    private var specialty_id: Int? = null
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
                currentFragment = PatientDetailFragment.newInstance()
                patient_id?.let { args.putInt("patient_id", it) }
            }
            Types.SPECIALTY.type -> {
                currentFragment = SpecialtyDetailFragment.newInstance()
                specialty_id?.let { args.putInt("specialty_id", it) }
            }
        }
        currentFragment.arguments = args
        return currentFragment
    }

    private fun getActivityParams() {
        type = intent.getIntExtra("type", 1)
        patient_id = intent.getIntExtra("patient_id", 0)
        specialty_id = intent.getIntExtra("specialty_id", 0)
    }
}