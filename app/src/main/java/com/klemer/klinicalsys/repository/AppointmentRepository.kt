package com.klemer.klinicalsys.repository

import com.klemer.klinicalsys.database.dao.AppointmentDAO
import com.klemer.klinicalsys.model.Appointment
import com.klemer.klinicalsys.model.AppointmentPOJO
import javax.inject.Inject

class AppointmentRepository @Inject constructor(private val dao: AppointmentDAO) {

    fun insert(appointment: Appointment) {
        dao.insert(appointment)
    }

    fun getAll(): List<AppointmentPOJO> {
        return dao.all()
    }

    fun byId(id: Int): AppointmentPOJO {
        return dao.byId(id)
    }

    fun delete(appointment: Appointment) {
        dao.delete(appointment)
    }

    fun filter(gender: String): List<AppointmentPOJO> {
        return dao.fetch(gender)
    }

    fun filter(doctorId: Int): List<AppointmentPOJO> {
        return dao.fetch(doctorId)
    }

    fun filter(gender: String, doctorId: Int): List<AppointmentPOJO> {
        return dao.fetch(gender, doctorId)
    }
}