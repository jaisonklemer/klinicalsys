package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.*
import com.klemer.klinicalsys.repository.AppointmentRepository
import com.klemer.klinicalsys.repository.DoctorRepository
import com.klemer.klinicalsys.repository.PatientRepository
import com.klemer.klinicalsys.repository.SpecialtyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentDetailViewModel @Inject constructor(
    private val patientsRepository: PatientRepository,
    private val doctorRepository: DoctorRepository,
    private val appointmentRepository: AppointmentRepository
) :
    ViewModel() {

    val patients = MutableLiveData<List<Patient>>()
    val appointment = MutableLiveData<AppointmentPOJO>()
    val doctors = MutableLiveData<List<DoctorPOJO>>()
    val actionComplete = MutableLiveData<Boolean>()


    fun getPatients() {
        patients.value = patientsRepository.getAllPatients()
    }

    fun getDoctors() {
        doctors.value = doctorRepository.all()
    }

    fun insertAppointment(patientId: Int, doctorId: Int) {
        val appointment = Appointment(patientFk = patientId, doctorFk = doctorId)
        appointmentRepository.insert(appointment)
        actionComplete.value = true
    }

    fun getById(id: Int) {
        appointment.value = appointmentRepository.byId(id)
    }

}