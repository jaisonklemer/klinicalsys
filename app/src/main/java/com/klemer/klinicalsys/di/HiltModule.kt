package com.klemer.klinicalsys.di

import android.content.Context
import com.klemer.klinicalsys.database.AppDatabase
import com.klemer.klinicalsys.database.dao.DoctorDAO
import com.klemer.klinicalsys.database.dao.PatientDAO
import com.klemer.klinicalsys.database.dao.SpecialtyDAO
import com.klemer.klinicalsys.repository.DoctorRepository
import com.klemer.klinicalsys.repository.PatientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HiltModule {

    @Provides
    fun providePatientDAO(@ApplicationContext context: Context): PatientDAO {
        return AppDatabase.getInstance(context).patientDAO()
    }

    @Provides
    fun provideSpecialtyDAO(@ApplicationContext context: Context): SpecialtyDAO {
        return AppDatabase.getInstance(context).specialtyDAO()
    }

    @Provides
    fun provideDoctorDAO(@ApplicationContext context: Context): DoctorDAO {
        return AppDatabase.getInstance(context).doctorDAO()
    }

    @Provides
    fun providePatientRepository(dao: PatientDAO): PatientRepository {
        return PatientRepository(dao)
    }

    @Provides
    fun provideDoctorRepository(dao: DoctorDAO): DoctorRepository {
        return DoctorRepository(dao)
    }
}