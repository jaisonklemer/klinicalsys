package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.Specialty
import com.klemer.klinicalsys.repository.SpecialtyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialtyViewModel @Inject constructor(private val repository: SpecialtyRepository) :
    ViewModel() {

    val specialties = MutableLiveData<List<Specialty>>()

    fun getAll() {
        specialties.value = repository.getAll()
    }

    fun insert(name: String) {
        val specialty = Specialty(name = name)
        repository.insert(specialty)
    }

}