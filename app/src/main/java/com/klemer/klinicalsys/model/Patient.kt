package com.klemer.klinicalsys.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Patient(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "patient_id")
    val id: Int = 0,

    @ColumnInfo(name = "patient_name")
    var name: String,

    @ColumnInfo(name = "patient_age")
    var age: Int,

    @ColumnInfo(name = "patient_gender")
    var gender: String
){
    override fun toString(): String {
        return name
    }
}