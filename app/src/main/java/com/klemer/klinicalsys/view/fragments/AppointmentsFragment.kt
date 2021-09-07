package com.klemer.klinicalsys.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.adapters.AppointmentAdapter
import com.klemer.klinicalsys.databinding.AppointmentsFragmentBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.model.AppointmentPOJO
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.DoctorPOJO
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

    private var doctorFilter: Int? = null
    private var genreFilter: String = ""

    private val adapter = AppointmentAdapter {
        APPOINTMENT_ID = it
        createControlActivity(false)
    }

    private val appointmentObserver = Observer<List<AppointmentPOJO>> {
        adapter.submitList(it)
    }

    private val doctorsObserver = Observer<List<DoctorPOJO>> {
        setupDoctorsDropdown(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_items, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_filter -> {
                showFilters()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showFilters() {
        val actual = binding.includeFilters.filtersLayout.visibility
        if (actual == View.GONE) {
            binding.includeFilters.filtersLayout.visibility =
                View.VISIBLE
        } else {
            binding.includeFilters.filtersLayout.visibility =
                View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppointmentsViewModel::class.java)
        binding = AppointmentsFragmentBinding.bind(view)

        setupButtonsClick()
        setupObservers()
        setupRecyclerView()
        setupGenresDropdown()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAppointments()
        viewModel.getDoctors()
    }

    private fun setupRecyclerView() {
        binding.appointmentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.appointmentsRecyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.appointments.observe(viewLifecycleOwner, appointmentObserver)
        viewModel.doctors.observe(viewLifecycleOwner, doctorsObserver)
    }

    private fun setupButtonsClick() {
        binding.fabAdd.setOnClickListener { createControlActivity(true) }
        binding.includeFilters.btnFilter.setOnClickListener { filter() }
        binding.includeFilters.btnClearFilters.setOnClickListener { clearFilters() }
    }

    private fun createControlActivity(isNew: Boolean) {
        val controlActivity = Intent(requireContext(), ControlActivity::class.java)
        controlActivity.putExtra("type", Types.APPOINTMENT.type)

        if (!isNew)
            APPOINTMENT_ID?.let { controlActivity.putExtra("appointment_id", it) }

        startActivity(controlActivity)
    }

    private fun setupDoctorsDropdown(list: List<DoctorPOJO>) {
        val doctors = list.map { it.doctor }

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, doctors)

        binding.includeFilters.acDoctorFilter.setAdapter(adapter)

        binding.includeFilters.acDoctorFilter.setOnItemClickListener { adapterView, view, i, l ->
            doctorFilter = (adapterView.getItemAtPosition(i) as Doctor).id
        }
    }

    private fun setupGenresDropdown() {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            PatientDetailFragment.GENRES
        )

        binding.includeFilters.acGenderFilter.setAdapter(adapter)
        binding.includeFilters.acGenderFilter.setOnItemClickListener { adapterView, view, i, l ->
            genreFilter = adapterView.getItemAtPosition(i).toString()
        }
    }

    private fun filter() {
        if (genreFilter.isNotEmpty() && doctorFilter == null) viewModel.filter(genreFilter)
        if (genreFilter.isEmpty() && doctorFilter != null) viewModel.filter(doctorFilter!!)
        if (genreFilter.isNotEmpty() && doctorFilter != null) viewModel.filter(
            genreFilter,
            doctorFilter!!
        )
    }

    private fun clearFilters() {
        binding.includeFilters.acGenderFilter.setText("", false)
        binding.includeFilters.acGenderFilter.clearFocus()
        binding.includeFilters.acDoctorFilter.setText("", false)
        binding.includeFilters.acDoctorFilter.clearFocus()
        viewModel.getAppointments()
    }

}