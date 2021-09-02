package com.klemer.klinicalsys.view.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.ActivityControlBinding
import com.klemer.klinicalsys.utils.extensions.replaceFragment
import com.klemer.klinicalsys.view.fragments.PatientDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ControlActivity : BaseActivity() {

    private var type: Int? = null
    private var patient_id: Int? = null

    private lateinit var binding: ActivityControlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityControlBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupActionBar()
        getActivityParams()
        replaceFragment(R.id.root_container_control, setupFirstFragment(), false)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupFirstFragment(): Fragment {
        val fragment = PatientDetailFragment.newInstance()
        val args = Bundle()
        patient_id?.let { args.putInt("patient_id", it) }
        fragment.arguments = args
        return fragment
    }

    private fun getActivityParams() {
        type = intent.getIntExtra("type", 1)
        patient_id = intent.getIntExtra("patient_id", 0)
    }
}