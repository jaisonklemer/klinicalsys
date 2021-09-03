package com.klemer.klinicalsys.database.dao

import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.klemer.klinicalsys.database.AppDatabase
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.model.Specialty
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class SpecialtyDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: SpecialtyDAO

    @Before
    fun setup() {
        database = inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.specialtyDAO()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun test_insert_specialty_should_return_true() {
        val specialty = Specialty(1, "Dentista")

        dao.insert(specialty)

        val result = dao.all()

        assertThat(result).contains(specialty)
    }

    @Test
    fun test_update_specialty_name() {
        val specialty = Specialty(1, "Dentista")
        dao.insert(specialty)

        specialty.name = "Geral"

        dao.update(specialty)

        val result = dao.getById(1)

        assertThat(result.name).isEqualTo("Geral")

    }

    @Test
    fun test_delete_patient() {
        val specialty = Specialty(1, "Dentista")
        dao.insert(specialty)

        dao.delete(specialty)

        val result = dao.getById(1)

        assertThat(result).isNull()
    }
}