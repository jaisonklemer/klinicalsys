package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.Specialty
import com.klemer.klinicalsys.repository.SpecialtyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SpecialtyDetailViewModel @Inject constructor(private val repository: SpecialtyRepository) :
    ViewModel() {

    val actionComplete = MutableLiveData<Boolean>()
    val specialty = MutableLiveData<Specialty>()

    fun getById(id: Int) {
        specialty.value = repository.getById(id)
    }

    fun insert(name: String) {
        val specialty = Specialty(name = name)
        repository.insert(specialty)
        actionComplete.value = true
    }

    fun update(specialty: Specialty) {
        repository.update(specialty)
        actionComplete.value = true
    }

    fun delete(specialty: Specialty) {
        repository.delete(specialty)
        actionComplete.value = true
    }

}