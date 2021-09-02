package com.klemer.klinicalsys.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.MainFragmentBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.view.activities.ControlActivity
import com.klemer.klinicalsys.viewmodel.MainViewModel

class MainFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding = MainFragmentBinding.bind(view)

        setButtonClick()
    }

    private fun setButtonClick() {
        binding.fabAdd.setOnClickListener { createControlActivity() }
    }

    private fun createControlActivity() {
        val controlActivity = Intent(requireContext(), ControlActivity::class.java)
        controlActivity.putExtra("type", Types.PATIENT.type)

        startActivity(controlActivity)
    }

}