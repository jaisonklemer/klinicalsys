package com.klemer.klinicalsys.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.adapters.DoctorsAdapter
import com.klemer.klinicalsys.databinding.DoctorsFragmentBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.model.DoctorPOJO
import com.klemer.klinicalsys.view.activities.ControlActivity
import com.klemer.klinicalsys.viewmodel.DoctorsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorsFragment : Fragment(R.layout.doctors_fragment) {
    private var DOCTOR_ID: Int? = null

    companion object {
        fun newInstance() = DoctorsFragment()
    }

    private lateinit var viewModel: DoctorsViewModel
    private lateinit var binding: DoctorsFragmentBinding
    private val doctorsAdapter = DoctorsAdapter {
        DOCTOR_ID = it
        createControlActivity(false)
    }

    private val doctorsList = Observer<List<DoctorPOJO>> {
        doctorsAdapter.submitList(it)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoctorsViewModel::class.java)
        binding = DoctorsFragmentBinding.bind(view)

        setupObservers()
        setupRecyclerView()
        setupButtonsClick()
    }

    private fun setupRecyclerView() {
        binding.doctorsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.doctorsRecyclerView.adapter = doctorsAdapter
    }

    private fun setupObservers() {
        viewModel.doctors.observe(viewLifecycleOwner, doctorsList)
    }

    private fun setupButtonsClick() {
        binding.fabAdd.setOnClickListener { createControlActivity(true) }
    }

    private fun createControlActivity(isNew: Boolean) {
        val controlActivity = Intent(requireContext(), ControlActivity::class.java)
        controlActivity.putExtra("type", Types.DOCTOR.type)

        if (!isNew)
            DOCTOR_ID?.let { controlActivity.putExtra("doctor_id", it) }

        startActivity(controlActivity)
    }

}