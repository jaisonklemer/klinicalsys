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
}