package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.repository.PatientRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PatientDetailViewModel @Inject constructor(
    private val repository: PatientRepository
) : ViewModel() {

    val patient = MutableLiveData<Patient?>()
    val actionComplete = MutableLiveData<Boolean>()
    val actionInfo = MutableLiveData<String>()

    fun savePatient(name: String, age: Int, genre: String) {
        repository.savePatient(name, age, genre)
        this.actionComplete()
    }

    fun getPatientById(id: Int) {
        patient.value = repository.getById(id)
    }

    fun updatePatient(patient: Patient) {
        repository.updatePatient(patient)
        this.actionComplete()
    }

    fun deletePatient(patient: Patient) {
        repository.deletePatient(patient)
        this.actionComplete()
        actionInfo.value = "Patient ID ${patient.id} is deleted permanently"
    }

    private fun actionComplete() {
        actionComplete.value = true
    }
}