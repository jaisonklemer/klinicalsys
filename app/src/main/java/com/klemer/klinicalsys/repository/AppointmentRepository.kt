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
}