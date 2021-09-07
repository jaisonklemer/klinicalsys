package com.klemer.klinicalsys.database.dao

import androidx.room.*
import com.klemer.klinicalsys.model.Patient

@Dao
interface PatientDAO {

    @Insert
    fun insert(patient: Patient)

    @Query("SELECT * FROM PATIENT ORDER BY patient_name")
    fun all(): List<Patient>

    @Update
    fun update(patient: Patient)

    @Delete
    fun delete(patient: Patient)

    @Query("SELECT * FROM PATIENT WHERE patient_id = :id")
    fun getById(id: Int) : Patient
}