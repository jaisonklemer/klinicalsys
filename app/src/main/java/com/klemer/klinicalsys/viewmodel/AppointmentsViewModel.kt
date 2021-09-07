package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.AppointmentPOJO
import com.klemer.klinicalsys.repository.AppointmentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(private val repository: AppointmentRepository) :
    ViewModel() {

    val appointments = MutableLiveData<List<AppointmentPOJO>>()

    fun getAppointments() {
        appointments.value = repository.getAll()
    }
}