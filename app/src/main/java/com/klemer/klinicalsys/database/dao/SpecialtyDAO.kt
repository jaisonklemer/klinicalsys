package com.klemer.klinicalsys.database.dao

import androidx.room.*
import com.klemer.klinicalsys.model.Specialty

@Dao
interface SpecialtyDAO {

    @Insert
    fun insert(specialty: Specialty)

    @Delete
    fun delete(specialty: Specialty)

    @Query("SELECT * FROM Specialty ORDER BY specialty_name")
    fun all(): List<Specialty>

    @Update
    fun update(specialty: Specialty)

    @Query("SELECT * FROM SPECIALTY WHERE specialty_id = :id")
    fun getById(id: Int): Specialty
}