package com.klemer.klinicalsys.database.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.klemer.klinicalsys.database.AppDatabase
import com.klemer.klinicalsys.model.Appointment
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.Patient
import com.klemer.klinicalsys.model.Specialty
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class AppointmentDAOTest {

    private lateinit var database: AppDatabase
    private lateinit var daoDoctor: DoctorDAO
    private lateinit var daoSpeciality: SpecialtyDAO
    private lateinit var daoAppointmentDAO: AppointmentDAO
    private lateinit var daoPatient: PatientDAO

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        daoDoctor = database.doctorDAO()
        daoSpeciality = database.specialtyDAO()
        daoAppointmentDAO = database.appointmentDAO()
        daoPatient = database.patientDAO()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun test_insert_appointment() {
        val patient = Patient(id = 1, name = "Jaison", gender = "Male", age = 23)
        daoPatient.insert(patient)

        val specialty = Specialty(id = 1, name = "Dentista")
        daoSpeciality.insert(specialty)

        val doctor = Doctor(id = 1, name = "João", specialtyFk = 1)
        daoDoctor.insert(listOf(doctor))

        val appointment = Appointment(id = 1, patientFk = patient.id, doctorFk = patient.id)
        daoAppointmentDAO.insert(appointment)

        val results = daoAppointmentDAO.all()

        assertThat(results).hasSize(1)
    }

    @Test
    fun test_delete_appointment() {
        val patient = Patient(id = 1, name = "Jaison", gender = "Male", age = 23)
        daoPatient.insert(patient)

        val specialty = Specialty(id = 1, name = "Dentista")
        daoSpeciality.insert(specialty)

        val doctor = Doctor(id = 1, name = "João", specialtyFk = 1)
        daoDoctor.insert(listOf(doctor))

        val appointment = Appointment(id = 1, patientFk = patient.id, doctorFk = patient.id)
        daoAppointmentDAO.insert(appointment)

        daoAppointmentDAO.delete(appointment)

        val results = daoAppointmentDAO.all()

        assertThat(results).hasSize(0)
    }

    @Test
    fun test_fetch_appointment_gender() {
        val patient = Patient(id = 1, name = "Jaison", gender = "Male", age = 23)
        val patient2 = Patient(id = 2, name = "Rafaela", gender = "Female", age = 23)
        val patient3 = Patient(id = 3, name = "Ana", gender = "Female", age = 27)

        daoPatient.insert(patient)
        daoPatient.insert(patient2)
        daoPatient.insert(patient3)

        val specialty = Specialty(id = 1, name = "Dentista")
        daoSpeciality.insert(specialty)

        val doctor = Doctor(id = 1, name = "João", specialtyFk = 1)
        daoDoctor.insert(listOf(doctor))

        val appointment = Appointment(id = 1, patientFk = patient.id, doctorFk = doctor.id)
        val appointment2 = Appointment(id = 2, patientFk = patient2.id, doctorFk = doctor.id)
        val appointment3 = Appointment(id = 3, patientFk = patient3.id, doctorFk = doctor.id)

        daoAppointmentDAO.insert(appointment)
        daoAppointmentDAO.insert(appointment2)
        daoAppointmentDAO.insert(appointment3)

        val results = daoAppointmentDAO.fetch("Female")

        assertThat(results).hasSize(2)
    }

    @Test
    fun test_fetch_appointment_by_doctor() {
        val patient = Patient(id = 1, name = "Jaison", gender = "Male", age = 23)
        val patient2 = Patient(id = 2, name = "Rafaela", gender = "Female", age = 23)
        val patient3 = Patient(id = 3, name = "Ana", gender = "Female", age = 27)

        daoPatient.insert(patient)
        daoPatient.insert(patient2)
        daoPatient.insert(patient3)

        val specialty = Specialty(id = 1, name = "Dentista")
        daoSpeciality.insert(specialty)

        val doctor = Doctor(id = 1, name = "João", specialtyFk = 1)
        val doctor2 = Doctor(id = 2, name = "Luis", specialtyFk = 1)
        daoDoctor.insert(listOf(doctor, doctor2))

        val appointment = Appointment(id = 1, patientFk = patient.id, doctorFk = doctor.id)
        val appointment2 = Appointment(id = 2, patientFk = patient2.id, doctorFk = doctor2.id)
        val appointment3 = Appointment(id = 3, patientFk = patient3.id, doctorFk = doctor.id)

        daoAppointmentDAO.insert(appointment)
        daoAppointmentDAO.insert(appointment2)
        daoAppointmentDAO.insert(appointment3)

        val results = daoAppointmentDAO.fetch(doctor.id)

        assertThat(results).hasSize(2)
    }

    @Test
    fun test_fetch_appointment_by_gender_and_doctor() {
        val patient = Patient(id = 1, name = "Jaison", gender = "Male", age = 23)
        val patient2 = Patient(id = 2, name = "Rafaela", gender = "Female", age = 23)
        val patient3 = Patient(id = 3, name = "Ana", gender = "Female", age = 27)

        daoPatient.insert(patient)
        daoPatient.insert(patient2)
        daoPatient.insert(patient3)

        val specialty = Specialty(id = 1, name = "Dentista")
        daoSpeciality.insert(specialty)

        val doctor = Doctor(id = 1, name = "João", specialtyFk = 1)
        val doctor2 = Doctor(id = 2, name = "Luis", specialtyFk = 1)
        daoDoctor.insert(listOf(doctor, doctor2))

        val appointment = Appointment(id = 1, patientFk = patient.id, doctorFk = doctor.id)
        val appointment2 = Appointment(id = 2, patientFk = patient2.id, doctorFk = doctor2.id)
        val appointment3 = Appointment(id = 3, patientFk = patient3.id, doctorFk = doctor.id)

        daoAppointmentDAO.insert(appointment)
        daoAppointmentDAO.insert(appointment2)
        daoAppointmentDAO.insert(appointment3)

        val results = daoAppointmentDAO.fetch("Female", doctor.id)

        assertThat(results).hasSize(1)
    }
}