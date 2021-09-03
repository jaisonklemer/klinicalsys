package com.klemer.klinicalsys.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Specialty(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "specialty_id")
    val id: Int = 0,
    @ColumnInfo(name = "specialty_name")
    var name: String,
)
