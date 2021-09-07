package com.klemer.klinicalsys.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.PatientListItemBinding
import com.klemer.klinicalsys.model.Patient

class PatientsListAdapter(val onClick: (Int) -> Unit) :

    ListAdapter<Patient, PatientsListVH>(PatientsListDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsListVH {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.patient_list_item, parent, false).apply {
                return PatientsListVH(this)
            }
    }

    override fun onBindViewHolder(holder: PatientsListVH, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
        holder.itemView.setOnClickListener { onClick(patient.id) }
    }

}

class PatientsListDiff : DiffUtil.ItemCallback<Patient>() {

    override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem == newItem
    }

}

class PatientsListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PatientListItemBinding.bind(itemView)

    fun bind(patient: Patient) {
        binding.patientNameTxtView.text = patient.name
        binding.tvPatientAge.text = "Age: ${patient.age}"
    }
}