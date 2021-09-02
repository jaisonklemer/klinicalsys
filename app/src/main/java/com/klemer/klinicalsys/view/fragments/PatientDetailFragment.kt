package com.klemer.klinicalsys.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.PatientDetailFragmentBinding

class PatientDetailFragment : Fragment(R.layout.patient_detail_fragment) {

    companion object {
        fun newInstance() = PatientDetailFragment()
    }

    private lateinit var viewModel: PatientDetailViewModel
    private lateinit var binding: PatientDetailFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientDetailViewModel::class.java)
        binding = PatientDetailFragmentBinding.bind(view)
    }

}