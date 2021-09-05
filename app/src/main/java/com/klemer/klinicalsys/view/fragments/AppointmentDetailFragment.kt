package com.klemer.klinicalsys.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.AppointmentDetailFragmentBinding
import com.klemer.klinicalsys.model.DoctorPOJO
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.viewmodel.AppointmentDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentDetailFragment : Fragment(R.layout.appointment_detail_fragment) {

    companion object {
        fun newInstance() = AppointmentDetailFragment()
    }

    private lateinit var viewModel: AppointmentDetailViewModel
    private lateinit var binding: AppointmentDetailFragmentBinding


    private val doctorObserver = Observer<List<DoctorPOJO>> {
        setupDoctorsDropdown(it)
    }

    private val patientsObserver = Observer<List<Patient>> {
        setupPatientsDropdown(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppointmentDetailViewModel::class.java)
        binding = AppointmentDetailFragmentBinding.bind(view)

        setupObservers()
        loadInitialData()
    }

    private fun setupObservers() {
        viewModel.doctors.observe(viewLifecycleOwner, doctorObserver)
        viewModel.patients.observe(viewLifecycleOwner, patientsObserver)
    }

    private fun loadInitialData() {
        viewModel.getDoctors()
        viewModel.getPatients()
    }

    private fun setupDoctorsDropdown(doctors: List<DoctorPOJO>) {
        val listOf = doctors.map { it.doctor }
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf)

        binding.appointmentDoctor.setAdapter(adapter)

    }

    private fun setupPatientsDropdown(patients: List<Patient>) {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, patients)

        binding.appointmentPatientName.setAdapter(adapter)
    }

}