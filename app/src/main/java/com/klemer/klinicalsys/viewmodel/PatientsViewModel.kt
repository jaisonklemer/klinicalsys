package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientsViewModel @Inject constructor(
    private val repository: PatientRepository
) : ViewModel() {
    val patients = MutableLiveData<List<Patient>>()

    fun getAllPatients() {
        patients.value = repository.getAllPatients()
    }
}