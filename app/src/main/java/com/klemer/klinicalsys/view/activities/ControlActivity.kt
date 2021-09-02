package com.klemer.klinicalsys.view.activities

import android.os.Bundle
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.utils.extensions.replaceFragment
import com.klemer.klinicalsys.view.fragments.PatientDetailFragment

class ControlActivity : BaseActivity() {

    private var type: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        setupActionBar()
        getActivityParams()
        println(type)
        replaceFragment(R.id.root_container_control, PatientDetailFragment.newInstance(), false)
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun getActivityParams() {
        type = intent.getIntExtra("type", 1)
    }
}