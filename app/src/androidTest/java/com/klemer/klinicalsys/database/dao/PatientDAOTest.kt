package com.klemer.klinicalsys.database.dao

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.klemer.klinicalsys.database.AppDatabase
import com.klemer.klinicalsys.model.Patient
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class PatientDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: PatientDAO

    @Before
    fun setup() {
        database = inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.patientDAO()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun test_insert_patient_should_return_true() {
        val patient = Patient(id = 1, name = "Jaison", age = 23, genre = "Male")

        dao.insert(patient)

        val results = dao.all()

        assertThat(results).contains(patient)
    }

    @Test
    fun test_update_patient_age_should_return_24() {
        val patient = Patient(id = 1, name = "Jaison", age = 23, genre = "Male")

        dao.insert(patient)

        patient.age = 24

        dao.update(patient)

        val result = dao.getById(1)

        assertThat(result.age).isEqualTo(24)

    }

    @Test
    fun test_delete_patient() {
        val patient = Patient(id = 1, name = "Jaison", age = 23, genre = "Male")

        dao.insert(patient)

        dao.delete(patient)

        val result = dao.getById(1)

        assertThat(result).isNull()

    }
}