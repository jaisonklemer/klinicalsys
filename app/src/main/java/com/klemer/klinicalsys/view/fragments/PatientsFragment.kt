package com.klemer.klinicalsys.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.adapters.PatientsListAdapter
import com.klemer.klinicalsys.databinding.MainFragmentBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.view.activities.ControlActivity
import com.klemer.klinicalsys.viewmodel.PatientDetailViewModel
import com.klemer.klinicalsys.viewmodel.PatientsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientsFragment : Fragment(R.layout.main_fragment) {

    companion object {
        fun newInstance() = PatientsFragment()
    }

    private var PATIENT_ID: Int? = null

    private lateinit var viewModel: PatientsViewModel
    private lateinit var binding: MainFragmentBinding

    private val patientAdapter = PatientsListAdapter() {
        PATIENT_ID = it
        createControlActivity(false)
    }

    private val patientsObserver = Observer<List<Patient>> {
        patientAdapter.update(it.toMutableList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientsViewModel::class.java)
        binding = MainFragmentBinding.bind(view)

        setupRecyclerView()
        setupObservers()
        setButtonClick()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllPatients()
    }

    private fun setupObservers() {
        viewModel.patients.observe(viewLifecycleOwner, patientsObserver)
    }

    private fun setButtonClick() {
        binding.fabAdd.setOnClickListener { createControlActivity(true) }
    }

    private fun setupRecyclerView() {
        binding.patientsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.patientsRecyclerView.adapter = patientAdapter
    }

    private fun createControlActivity(isNew: Boolean) {
        val controlActivity = Intent(requireContext(), ControlActivity::class.java)
        controlActivity.putExtra("type", Types.PATIENT.type)

        if (!isNew)
            PATIENT_ID?.let { controlActivity.putExtra("patient_id", it) }

        startActivity(controlActivity)
    }

}