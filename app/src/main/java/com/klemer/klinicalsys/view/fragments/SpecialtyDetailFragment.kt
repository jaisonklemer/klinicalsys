package com.klemer.klinicalsys.view.fragments

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.SpecialtyDetailFragmentBinding
import com.klemer.klinicalsys.model.Specialty
import com.klemer.klinicalsys.viewmodel.SpecialtyDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialtyDetailFragment : Fragment(R.layout.specialty_detail_fragment) {

    companion object {
        fun newInstance() = SpecialtyDetailFragment()
    }

    private lateinit var viewModel: SpecialtyDetailViewModel
    private lateinit var binding: SpecialtyDetailFragmentBinding
    private var SPECIALTY_ID: Int? = null
    private var specialty: Specialty? = null


    private val specialtyObserver = Observer<Specialty> {
        specialty = it
        bindData(it)
    }
    private val actionCompleteObserver = Observer<Boolean> { requireActivity().finish() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpecialtyDetailViewModel::class.java)
        binding = SpecialtyDetailFragmentBinding.bind(view)

        setupOpbservers()
        checkParams()
        setupButtonsClick()
    }

    private fun deleteSpecialty() {
        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.you_cannot_undo_it))
            .setIcon(R.drawable.ic_trash)
            .setTitle(getString(R.string.you_re_sure))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.delete(specialty!!)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
            .create()
            .show()
    }

    private fun setupOpbservers() {
        viewModel.specialty.observe(viewLifecycleOwner, specialtyObserver)
        viewModel.actionComplete.observe(viewLifecycleOwner, actionCompleteObserver)
    }

    private fun setupButtonsClick() {
        binding.saveButton.setOnClickListener { saveSpecialty() }
        binding.deleteButton.setOnClickListener { deleteSpecialty() }
    }

    private fun getParams() {
        arguments?.let {
            if (it.getInt("specialty_id") != 0)
                SPECIALTY_ID = it.getInt("specialty_id")
        }
    }

    private fun checkParams() {
        if (SPECIALTY_ID != null) {
            getSpecialty()
        }
    }

    private fun getSpecialty() {
        SPECIALTY_ID?.let { viewModel.getById(it) }
    }


    private fun bindData(it: Specialty) {
        binding.specialtyNameEditText.setText(it.name)
        binding.specialtyID.setText(it.id.toString())

        binding.deleteButton.isEnabled = true
    }

    private fun saveSpecialty() {
        val name = binding.specialtyNameEditText.text.toString()

        if (name.isNotEmpty()) {
            if (SPECIALTY_ID != null) {
                viewModel.update(this.buildSpecialty(name))
            } else {
                viewModel.insert(name = name)
            }
        }
    }

    private fun buildSpecialty(name: String): Specialty {
        specialty?.id = SPECIALTY_ID!!
        specialty?.name = name
        return specialty!!
    }

}