package com.klemer.klinicalsys.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.adapters.AppointmentAdapter
import com.klemer.klinicalsys.databinding.AppointmentsFragmentBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.model.AppointmentPOJO
import com.klemer.klinicalsys.view.activities.ControlActivity
import com.klemer.klinicalsys.viewmodel.AppointmentsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentsFragment : Fragment(R.layout.appointments_fragment) {

    companion object {
        fun newInstance() = AppointmentsFragment()
    }

    private lateinit var viewModel: AppointmentsViewModel
    private lateinit var binding: AppointmentsFragmentBinding
    private var APPOINTMENT_ID: Int? = null
    private val adapter = AppointmentAdapter {
        APPOINTMENT_ID = it
        createControlActivity(false)
    }

    private val appointmentObserver = Observer<List<AppointmentPOJO>> {
        adapter.submitList(it)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppointmentsViewModel::class.java)
        binding = AppointmentsFragmentBinding.bind(view)

        setupButtonsClick()
        setupObservers()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAppointments()
    }

    private fun setupRecyclerView() {
        binding.appointmentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.appointmentsRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.appointments.observe(viewLifecycleOwner, appointmentObserver)
    }

    private fun setupButtonsClick() {
        binding.fabAdd.setOnClickListener { createControlActivity(true) }
    }

    private fun createControlActivity(isNew: Boolean) {
        val controlActivity = Intent(requireContext(), ControlActivity::class.java)
        controlActivity.putExtra("type", Types.APPOINTMENT.type)

        if (!isNew)
            APPOINTMENT_ID?.let { controlActivity.putExtra("appointment_id", it) }

        startActivity(controlActivity)
    }

}