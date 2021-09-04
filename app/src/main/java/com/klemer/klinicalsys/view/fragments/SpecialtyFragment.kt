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
import com.klemer.klinicalsys.adapters.SpecialtiesAdapter
import com.klemer.klinicalsys.databinding.SpecialtyFragmentBinding
import com.klemer.klinicalsys.databinding.SpecialtyListItemBinding
import com.klemer.klinicalsys.enums.Types
import com.klemer.klinicalsys.model.Specialty
import com.klemer.klinicalsys.view.activities.ControlActivity
import com.klemer.klinicalsys.viewmodel.SpecialtyViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialtyFragment : Fragment(R.layout.specialty_fragment) {
    private var SPECIALTY_ID: Int? = null

    companion object {
        fun newInstance() = SpecialtyFragment()
    }

    private lateinit var viewModel: SpecialtyViewModel
    private lateinit var binding: SpecialtyFragmentBinding
    private val adapter = SpecialtiesAdapter() {
        SPECIALTY_ID = it
        createControlActivity(false)
    }

    private val specialtiesObserver = Observer<List<Specialty>> {
        adapter.submitList(it)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SpecialtyViewModel::class.java)
        binding = SpecialtyFragmentBinding.bind(view)

        setupObservers()
        setupRecyclerView()

        setupButtonsClick()

    }

    private fun setupObservers() {
        viewModel.specialties.observe(viewLifecycleOwner, specialtiesObserver)
    }

    private fun setupRecyclerView() {
        binding.specialtiesRecyclerView.adapter = adapter
        binding.specialtiesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupButtonsClick() {
        binding.fabAdd.setOnClickListener { createControlActivity(true) }
    }

    private fun createControlActivity(isNew: Boolean) {
        val controlActivity = Intent(requireContext(), ControlActivity::class.java)
        controlActivity.putExtra("type", Types.SPECIALTY.type)

        if (!isNew)
            SPECIALTY_ID?.let { controlActivity.putExtra("specialty_id", it) }

        startActivity(controlActivity)
    }

}