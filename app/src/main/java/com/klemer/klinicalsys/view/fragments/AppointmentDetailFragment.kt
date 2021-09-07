package com.klemer.klinicalsys.view.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.AppointmentDetailFragmentBinding
import com.klemer.klinicalsys.model.*
import com.klemer.klinicalsys.viewmodel.AppointmentDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AppointmentDetailFragment : Fragment(R.layout.appointment_detail_fragment) {

    companion object {
        fun newInstance() = AppointmentDetailFragment()
    }

    private lateinit var viewModel: AppointmentDetailViewModel
    private lateinit var binding: AppointmentDetailFragmentBinding
    private var DOCTOR_ID: Int? = null
    private var PATIENT_ID: Int? = null
    private var APOINTMENT_ID: Int? = null
    private var appointment: Appointment? = null


    private val doctorObserver = Observer<List<DoctorPOJO>> {
        setupDoctorsDropdown(it)
    }

    private val appointmentObserver = Observer<AppointmentPOJO> {
        if (it != null) {
            appointment = it.appointment
            bindLoadedData(it)
        }
    }

    private val patientsObserver = Observer<List<Patient>> {
        setupPatientsDropdown(it)
    }

    private val actionObserver = Observer<Boolean> {
        if (it) requireActivity().finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppointmentDetailViewModel::class.java)
        binding = AppointmentDetailFragmentBinding.bind(view)

        checkParams()
        setupObservers()
        loadInitialData()
        setupButtonsClick()
    }

    private fun getParams() {
        arguments?.let {
            if (it.getInt("appointment_id") != 0)
                APOINTMENT_ID = it.getInt("appointment_id")
        }
    }

    private fun checkParams() {
        if (APOINTMENT_ID != null) {
            getAppointment()
        }
    }

    private fun getAppointment() {
        APOINTMENT_ID?.let { viewModel.getById(it) }
    }

    private fun setupObservers() {
        viewModel.doctors.observe(viewLifecycleOwner, doctorObserver)
        viewModel.patients.observe(viewLifecycleOwner, patientsObserver)
        viewModel.actionComplete.observe(viewLifecycleOwner, actionObserver)
        viewModel.appointment.observe(viewLifecycleOwner, appointmentObserver)
    }

    private fun loadInitialData() {
        viewModel.getDoctors()
        viewModel.getPatients()
    }

    private fun setupButtonsClick() {
        binding.saveButton.setOnClickListener { saveAppointment() }
        binding.deleteButton.setOnClickListener { deleteAppointment() }
    }

    private fun setupDoctorsDropdown(doctors: List<DoctorPOJO>) {
        val listOf = doctors.map { it.doctor }
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, listOf)

        binding.appointmentDoctor.setAdapter(adapter)

        binding.appointmentDoctor.setOnItemClickListener { adapterView, view, i, l ->
            DOCTOR_ID = (adapterView.getItemAtPosition(i) as Doctor).id
        }
    }

    private fun setupPatientsDropdown(patients: List<Patient>) {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, patients)

        binding.appointmentPatientName.setAdapter(adapter)

        binding.appointmentPatientName.setOnItemClickListener { adapterView, view, i, l ->
            PATIENT_ID = (adapterView.getItemAtPosition(i) as Patient).id
        }
    }

    private fun saveAppointment() {
        if (PATIENT_ID != null && DOCTOR_ID != null) {
            viewModel.insertAppointment(PATIENT_ID!!, DOCTOR_ID!!)
        }
    }

    private fun bindLoadedData(appointment: AppointmentPOJO) {
        binding.deleteButton.isEnabled = true

        binding.etAppointmentId.setText(appointment.appointment.id.toString())
        binding.appointmentDoctor.setText(appointment.doctor.name, false)
        binding.appointmentPatientName.setText(appointment.patient.name, false)

    }

    private fun deleteAppointment() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.you_cannot_undo_it))
            .setIcon(R.drawable.ic_trash)
            .setTitle(getString(R.string.you_re_sure))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.delete(appointment!!)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .create()
            .show()
    }

}