package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.DoctorPOJO
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.model.Specialty
import com.klemer.klinicalsys.repository.DoctorRepository
import com.klemer.klinicalsys.repository.PatientRepository
import com.klemer.klinicalsys.repository.SpecialtyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppointmentDetailViewModel @Inject constructor(
    private val patientsRepository: PatientRepository,
    private val doctorRepository: DoctorRepository
) :
    ViewModel() {

    val patients = MutableLiveData<List<Patient>>()
    val doctors = MutableLiveData<List<DoctorPOJO>>()


    fun getPatients() {
        patients.value = patientsRepository.getAllPatients()
    }

    fun getDoctors() {
        doctors.value = doctorRepository.all()
    }

}