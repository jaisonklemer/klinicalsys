package com.klemer.klinicalsys.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.klemer.klinicalsys.database.dao.PatientDAO
import com.klemer.klinicalsys.database.dao.SpecialtyDAO
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.model.Specialty

@Database(entities = [Patient::class, Specialty::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun patientDAO(): PatientDAO
    abstract fun specialtyDAO(): SpecialtyDAO

    companion object {

        fun getInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "klinical_sys_db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }


}