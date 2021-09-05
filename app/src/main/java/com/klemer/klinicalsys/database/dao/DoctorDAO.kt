package com.klemer.klinicalsys.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.DoctorPOJO

@Dao
interface DoctorDAO {

    @Insert(onConflict = ABORT)
    fun insert(doctorsList: List<Doctor>)

    @Delete
    fun delete(doctor: Doctor)

    @Update
    fun update(doctor: Doctor)

    @Transaction
    @Query("Select * from Doctor where doctor_id = :id")
    fun fetch(id: Int): DoctorPOJO

    @Transaction
    @Query("Select * from Doctor order by doctor_name")
    fun fetch(): List<DoctorPOJO>

}