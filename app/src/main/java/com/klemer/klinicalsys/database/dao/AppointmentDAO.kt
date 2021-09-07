package com.klemer.klinicalsys.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.klemer.klinicalsys.model.Appointment
import com.klemer.klinicalsys.model.AppointmentPOJO

@Dao
interface AppointmentDAO {

    @Insert(onConflict = ABORT)
    fun insert(appointment: Appointment)

    @Delete
    fun delete(appointment: Appointment)

    @Update
    fun update(appointment: Appointment)

    @Transaction
    @Query("SELECT * FROM APPOINTMENT")
    fun all(): List<AppointmentPOJO>

    @Transaction
    @Query("SELECT * FROM APPOINTMENT WHERE appointment_id = :id ")
    fun byId(id: Int): AppointmentPOJO

    @Transaction
    @Query("Select * from Appointment inner join Patient on patient.patient_id = patientFk where patient_genre = :gender")
    fun fetch(gender: String): List<AppointmentPOJO>

    @Transaction
    @Query("SELECT * FROM Appointment INNER JOIN Doctor on doctor.doctor_id = doctorFk WHERE doctor_id = :doctorId")
    fun fetch(doctorId: Int): List<AppointmentPOJO>

    @Transaction
    @Query(
        "SELECT * FROM Appointment " +
                "INNER JOIN Doctor ON doctor.doctor_id = doctorFk " +
                "INNER JOIN Patient ON patient.patient_id = patientFk " +
                "WHERE patient_genre = :gender " +
                "AND doctor_id = :doctorId"
    )
    fun fetch(gender: String, doctorId: Int): List<AppointmentPOJO>

}