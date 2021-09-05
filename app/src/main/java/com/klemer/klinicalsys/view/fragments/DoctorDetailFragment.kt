package com.klemer.klinicalsys.view.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.DoctorDetailFragmentBinding
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.DoctorPOJO
import com.klemer.klinicalsys.model.Specialty
import com.klemer.klinicalsys.viewmodel.DoctorDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DoctorDetailFragment : Fragment(R.layout.doctor_detail_fragment) {

    companion object {
        fun newInstance() = DoctorDetailFragment()
    }

    private lateinit var viewModel: DoctorDetailViewModel
    private lateinit var binding: DoctorDetailFragmentBinding
    private var DOCTOR_ID: Int? = null
    private var SPECIALTY: Specialty? = null
    private var doctor: Doctor? = null

    private val doctorObserver = Observer<DoctorPOJO> {
        doctor = it.doctor
        bindData(it)
    }

    private val specialtiesObserver = Observer<List<Specialty>> {
        setupAutoComplete(it)
    }

    private val actionCompleteObserver = Observer<Boolean> {
        requireActivity().finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DoctorDetailViewModel::class.java)
        binding = DoctorDetailFragmentBinding.bind(view)

        checkParams()
        setupOpbservers()
        viewModel.getAllSpecialties()
        setupButtonsClick()
    }

    private fun setupButtonsClick() {
        binding.saveButton.setOnClickListener { saveDoctor() }
        binding.deleteButton.setOnClickListener { deleteDoctor() }
    }

    private fun setupOpbservers() {
        viewModel.doctor.observe(viewLifecycleOwner, doctorObserver)
        viewModel.actionComplete.observe(viewLifecycleOwner, actionCompleteObserver)
        viewModel.specialties.observe(viewLifecycleOwner, specialtiesObserver)
    }

    private fun getParams() {
        arguments?.let {
            if (it.getInt("doctor_id") != 0)
                DOCTOR_ID = it.getInt("doctor_id")
        }
    }

    private fun checkParams() {
        if (DOCTOR_ID != null) {
            getDoctor()
        }
    }

    private fun getDoctor() {
        DOCTOR_ID?.let { viewModel.getBydId(it) }
    }

    private fun setupAutoComplete(specialties: List<Specialty>) {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, specialties)

        binding.doctorSpecialty.setAdapter(adapter)
        binding.doctorSpecialty.setOnItemClickListener { adapterView, p1, position, p3 ->

            SPECIALTY = adapterView.getItemAtPosition(position) as Specialty
        }
    }

    private fun saveDoctor() {
        val name = binding.doctorNameEditText.text.toString()
        if (name.isNotEmpty()) {
            if (DOCTOR_ID != null) {
                viewModel.update(this.buildDoctor(name))
            } else {
                viewModel.insert(name, SPECIALTY!!.id)
            }
        }
    }

    private fun buildDoctor(name: String): Doctor {
        doctor = Doctor(id = DOCTOR_ID!!, name = name, specialtyFk = SPECIALTY!!.id)


        return doctor!!
    }

    private fun deleteDoctor() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.you_cannot_undo_it))
            .setIcon(R.drawable.ic_trash)
            .setTitle(getString(R.string.you_re_sure))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.delete(doctor!!)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .create()
            .show()
    }

    private fun bindData(it: DoctorPOJO) {
        binding.doctorId.setText(it.doctor!!.id.toString())
        binding.doctorNameEditText.setText(it.doctor.name)
        binding.doctorSpecialty.setText(it.speciality.toString())

        binding.deleteButton.isEnabled = true
    }

}