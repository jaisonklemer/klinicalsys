package com.klemer.klinicalsys.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.klemer.klinicalsys.model.DoctorPOJO
import com.klemer.klinicalsys.repository.DoctorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DoctorsViewModel @Inject constructor(private val repository: DoctorRepository) : ViewModel() {

    val doctors = MutableLiveData<List<DoctorPOJO>>()

    fun getAll() {
        doctors.value = repository.all()
    }
}