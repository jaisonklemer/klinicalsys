package com.klemer.klinicalsys.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.klemer.klinicalsys.database.AppDatabase
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.Specialty
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class DoctorDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var daoDoctor: DoctorDAO
    private lateinit var daoSpeciality: SpecialtyDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        daoDoctor = database.doctorDAO()
        daoSpeciality = database.specialtyDAO()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun testing_insert() {
        val s1 = Specialty(1, "s1")
        val s2 = Specialty(2, "s2")
        daoSpeciality.insert(s1)
        daoSpeciality.insert(s2)

        val p1 = Doctor(name = "Jaison", specialtyFk = s2.id)
        val p2 = Doctor(name = "Luis", specialtyFk = s1.id)
        val listToInsert = arrayListOf(p1, p2)

        daoDoctor.insert(listToInsert)

        val results = daoDoctor.fetch()
        assertThat(results).hasSize(listToInsert.size)
    }
}