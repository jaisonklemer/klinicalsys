package com.klemer.klinicalsys.repository

import com.klemer.klinicalsys.database.dao.PatientDAO
import com.klemer.klinicalsys.model.Patient
import javax.inject.Inject

class PatientRepository @Inject constructor(
    private val dao: PatientDAO
) {

    fun savePatient(name: String, age: Int, gender: String) {
        val patient = Patient(name = name, age = age, gender = gender)
        dao.insert(patient)
    }

    fun getAllPatients(): List<Patient> {
        return dao.all()
    }

    fun getById(id: Int): Patient {
        return dao.getById(id)
    }

    fun updatePatient(patient: Patient) {
        dao.update(patient)
    }

    fun deletePatient(patient: Patient) {
        dao.delete(patient)
    }
}