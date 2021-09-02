package com.klemer.klinicalsys.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.klemer.klinicalsys.database.dao.PatientDAO
import com.klemer.klinicalsys.model.Patient

@Database(entities = [Patient::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun patientDAO(): PatientDAO

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