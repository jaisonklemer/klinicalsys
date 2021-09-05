package com.klemer.klinicalsys.model

import androidx.room.*

@Entity
data class Doctor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "doctor_id")
    var id: Int = 0,

    @ColumnInfo(name = "doctor_name")
    var name: String,

    var specialtyFk: Int
) {
    override fun toString(): String {
        return name
    }
}

data class DoctorPOJO(
    @Embedded
    val doctor: Doctor?,
    @Relation(
        parentColumn = "specialtyFk",
        entityColumn = "specialty_id"
    )
    var speciality: Specialty?
)
