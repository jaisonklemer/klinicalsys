package com.klemer.klinicalsys.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.PatientListItemBinding
import com.klemer.klinicalsys.model.Patient

class PatientsListAdapter(val onClick: (Int) -> Unit) : RecyclerView.Adapter<PatientsListVH>() {
    private val patientsList = mutableListOf<Patient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsListVH {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.patient_list_item, parent, false).apply {
                return PatientsListVH(this)
            }
    }

    override fun onBindViewHolder(holder: PatientsListVH, position: Int) {
        holder.bind(patientsList[position])

        holder.itemView.setOnClickListener { onClick(patientsList[position].id) }
    }

    override fun getItemCount() = patientsList.size

    fun update(list: MutableList<Patient>) {
        patientsList.clear()
        patientsList.addAll(list)
        notifyDataSetChanged()
    }
}

class PatientsListVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = PatientListItemBinding.bind(itemView)

    fun bind(patient: Patient) {
        binding.patientNameTxtView.text = patient.name
        binding.patientAgeTxtView.text = patient.age.toString()
    }
}