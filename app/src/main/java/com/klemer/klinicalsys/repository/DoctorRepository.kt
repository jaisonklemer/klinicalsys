package com.klemer.klinicalsys.repository

import com.klemer.klinicalsys.database.dao.DoctorDAO
import com.klemer.klinicalsys.model.Doctor
import com.klemer.klinicalsys.model.DoctorPOJO
import javax.inject.Inject

class DoctorRepository @Inject constructor(private val dao: DoctorDAO) {

    fun all(): List<DoctorPOJO> {
        return dao.fetch()
    }

    fun byId(id: Int): DoctorPOJO {
        return dao.fetch(id)
    }

    fun update(doctor: Doctor) {
        dao.update(doctor)
    }

    fun insert(doctor: Doctor) {
        dao.insert(listOf(doctor))
    }

    fun delete(doctor: Doctor) {
        dao.delete(doctor)
    }

}
