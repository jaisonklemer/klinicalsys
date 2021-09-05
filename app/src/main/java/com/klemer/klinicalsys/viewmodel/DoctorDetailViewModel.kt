package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.DoctorPOJO
import com.klemer.klinicalsys.model.Specialty
import com.klemer.klinicalsys.repository.DoctorRepository
import com.klemer.klinicalsys.repository.SpecialtyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorDetailViewModel @Inject constructor(
    private val doctorRepository: DoctorRepository,
    private val specialtyRepository: SpecialtyRepository
) :
    ViewModel() {

    val actionComplete = MutableLiveData<Boolean>()
    val doctor = MutableLiveData<DoctorPOJO>()
    val specialties = MutableLiveData<List<Specialty>>()

    fun getBydId(id: Int) {
        doctor.value = doctorRepository.byId(id)
    }

    fun getAllSpecialties() {
        specialties.value = specialtyRepository.getAll()
    }

    fun update(doctor: Doctor) {
        doctorRepository.update(doctor)
        actionComplete.value = true
    }

    fun insert(name: String, specialtyFk: Int) {
        val doctor = Doctor(name = name, specialtyFk = specialtyFk)
        doctorRepository.insert(doctor)
        actionComplete.value = true
    }

    fun delete(doctor: Doctor) {
        doctorRepository.delete(doctor)
        actionComplete.value = true
    }

}