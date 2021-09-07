package com.klemer.klinicalsys.model

import androidx.room.*

@Entity
data class Appointment(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "appointment_id")
    val id: Int = 0,
    var patientFk: Int,
    var doctorFk: Int
)

data class AppointmentPOJO(
    @Embedded
    val appointment: Appointment,

    @Relation(
        parentColumn = "patientFk",
        entityColumn = "patient_id"
    )
    val patient: Patient,

    @Relation(
        parentColumn = "doctorFk",
        entityColumn = "doctor_id"
    )
    val doctor: Doctor
)
