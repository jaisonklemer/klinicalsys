package com.klemer.klinicalsys.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.AppointmentListItemBinding
import com.klemer.klinicalsys.model.AppointmentPOJO

class AppointmentAdapter(val onClick: (Int) -> Unit) :
    ListAdapter<AppointmentPOJO, AppointmentVH>(AppointmentDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentVH {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.appointment_list_item, parent, false).apply {
                return AppointmentVH(this)
            }
    }

    override fun onBindViewHolder(holder: AppointmentVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { onClick(item.appointment.id) }
    }

}

class AppointmentDiffUtil : DiffUtil.ItemCallback<AppointmentPOJO>() {
    override fun areItemsTheSame(oldItem: AppointmentPOJO, newItem: AppointmentPOJO): Boolean {
        return (oldItem.appointment.id == newItem.appointment.id)
                && (oldItem.doctor.id == newItem.doctor.id)
    }

    override fun areContentsTheSame(oldItem: AppointmentPOJO, newItem: AppointmentPOJO): Boolean {
        return oldItem == newItem
    }

}

class AppointmentVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = AppointmentListItemBinding.bind(itemView)

    fun bind(appointment: AppointmentPOJO) {
        binding.tvDoctorName.text = appointment.doctor.name
        binding.tvPatientName.text = appointment.patient.name
    }
}