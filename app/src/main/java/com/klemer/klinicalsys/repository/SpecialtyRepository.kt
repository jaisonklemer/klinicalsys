package com.klemer.klinicalsys.repository

import com.klemer.klinicalsys.database.dao.SpecialtyDAO
import com.klemer.klinicalsys.model.Specialty
import javax.inject.Inject

class SpecialtyRepository @Inject constructor(val dao: SpecialtyDAO) {

    fun insert(specialty: Specialty) {
        dao.insert(specialty)
    }

    fun getAll(): List<Specialty> {
        return dao.all()
    }

    fun getById(id: Int): Specialty {
        return dao.getById(id)
    }

    fun update(specialty: Specialty) {
        dao.update(specialty)
    }

}
