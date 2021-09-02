package com.klemer.klinicalsys.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.viewmodel.AppointmentsViewModel

class AppointmentsFragment : Fragment() {

    companion object {
        fun newInstance() = AppointmentsFragment()
    }

    private lateinit var viewModel: AppointmentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.appointments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AppointmentsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}