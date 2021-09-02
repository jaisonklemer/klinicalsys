package com.klemer.klinicalsys.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.ActivityMainBinding
import com.klemer.klinicalsys.utils.extensions.replaceFragment
import com.klemer.klinicalsys.view.fragments.MainFragment
import com.klemer.klinicalsys.view.fragments.PatientDetailFragment

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        replaceFragment(R.id.root_container, MainFragment.newInstance(), false)
    }
}