package com.klemer.klinicalsys.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.AppointmentsFragmentBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.view.activities.ControlActivity
import com.klemer.klinicalsys.viewmodel.AppointmentsViewModel

class AppointmentsFragment : Fragment(R.layout.appointments_fragment) {

    companion object {
        fun newInstance() = AppointmentsFragment()
    }

    private lateinit var viewModel: AppointmentsViewModel
    private lateinit var binding: AppointmentsFragmentBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppointmentsViewModel::class.java)
        binding = AppointmentsFragmentBinding.bind(view)

        setupButtonsClick()
    }

    private fun setupButtonsClick() {
        binding.fabAdd.setOnClickListener { createControlActivity(true) }
    }

    private fun createControlActivity(isNew: Boolean) {
        val controlActivity = Intent(requireContext(), ControlActivity::class.java)
        controlActivity.putExtra("type", Types.APPOINTMENT.type)

//        if (!isNew)
//            DOCTOR_ID?.let { controlActivity.putExtra("doctor_id", it) }

        startActivity(controlActivity)
    }

}