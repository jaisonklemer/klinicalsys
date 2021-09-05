package com.klemer.klinicalsys.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Specialty(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "specialty_id")
    var id: Int = 0,
    @ColumnInfo(name = "specialty_name")
    var name: String,
) {
    override fun toString(): String {
        return name
    }
}
