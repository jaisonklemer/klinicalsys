package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.AppointmentPOJO
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.DoctorPOJO
import com.klemer.klinicalsys.repository.AppointmentRepository
import com.klemer.klinicalsys.repository.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentsViewModel @Inject constructor(
    private val repository: AppointmentRepository,
    private val doctorsRepository: DoctorRepository
) :
    ViewModel() {

    val appointments = MutableLiveData<List<AppointmentPOJO>>()
    val doctors = MutableLiveData<List<DoctorPOJO>>()

    fun getAppointments() {
        appointments.value = repository.getAll()
    }

    fun getDoctors() {
        doctors.value = doctorsRepository.all()
    }

    fun filter(genre: String) {
        appointments.value = repository.filter(genre)
    }

    fun filter(doctorId: Int) {
        appointments.value = repository.filter(doctorId)
    }

    fun filter(genre: String, doctorId: Int) {
        appointments.value = repository.filter(genre, doctorId)
    }
}