package com.klemer.klinicalsys.view.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.PatientDetailFragmentBinding
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.viewmodel.PatientDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientDetailFragment : Fragment(R.layout.patient_detail_fragment) {
    private var PATIENT_ID: Int? = null
    private var patient: Patient? = null

    companion object {
        fun newInstance() = PatientDetailFragment()
    }

    private lateinit var viewModel: PatientDetailViewModel
    private lateinit var binding: PatientDetailFragmentBinding

    private val patientObserver = Observer<Patient?> {
        if (it != null) {
            patient = it
            loadPatientsDetail(it)
        }
    }

    private val actionComplete = Observer<Boolean> {
        if (it) {
            requireActivity().finish()
        }
    }

    private val actionInfoMsg = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientDetailViewModel::class.java)
        binding = PatientDetailFragmentBinding.bind(view)

        setupObservers()
        checkParams()
        setButtonClickListeners()
    }

    private fun setupObservers() {
        viewModel.patient.observe(viewLifecycleOwner, patientObserver)
        viewModel.actionComplete.observe(viewLifecycleOwner, actionComplete)
        viewModel.actionInfo.observe(viewLifecycleOwner, actionInfoMsg)
    }

    private fun savePatient() {
        val name = binding.patientNameEditText.text.toString()
        val age = (binding.patientAgeEditText.text.toString()).toInt()
        val genre = binding.patientGenreEditText.text.toString()

        if (name.isNotEmpty() && genre.isNotEmpty()) {
            if (PATIENT_ID != null) {
                viewModel.updatePatient(this.buildPatient())
            } else {
                viewModel.savePatient(name = name, age = age, genre = genre)
            }
        }
    }

    private fun setButtonClickListeners() {
        binding.saveButton.setOnClickListener { savePatient() }

        binding.deleteButton.setOnClickListener { deletePatient() }
    }

    private fun buildPatient(): Patient {
        val name = binding.patientNameEditText.text.toString()
        val age = (binding.patientAgeEditText.text.toString()).toInt()
        val genre = binding.patientGenreEditText.text.toString()

        patient?.name = name
        patient?.age = age
        patient?.genre = genre
        return patient!!
    }

    private fun deletePatient() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.you_cannot_undo_it))
            .setIcon(R.drawable.ic_trash)
            .setTitle(getString(R.string.you_re_sure))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.deletePatient(patient!!)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .create()
            .show()
    }

    private fun getParams() {
        arguments?.let {
            if (it.getInt("patient_id") != 0)
                PATIENT_ID = it.getInt("patient_id")
        }
    }

    private fun checkParams() {
        if (PATIENT_ID != null) {
            getPatient()
        }
    }

    private fun getPatient() {
        PATIENT_ID?.let { viewModel.getPatientById(it) }
    }

    private fun loadPatientsDetail(patient: Patient?) {
        binding.patientNameEditText.setText(patient?.name)
        binding.patientAgeEditText.setText(patient?.age.toString())
        binding.patientGenreEditText.setText(patient?.genre)

        binding.deleteButton.isEnabled = true
    }
}