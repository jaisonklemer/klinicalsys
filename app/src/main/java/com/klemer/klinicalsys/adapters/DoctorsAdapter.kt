package com.klemer.klinicalsys.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.SpecialtyListItemBinding

import com.klemer.klinicalsys.model.DoctorPOJO

class DoctorsAdapter(private val itemClick: (Int) -> Unit) :
    ListAdapter<DoctorPOJO, DoctorsVH>(DoctorsDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsVH {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.specialty_list_item, parent, false).apply {
                return DoctorsVH(this)
            }
    }

    override fun onBindViewHolder(holder: DoctorsVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { itemClick(item.doctor!!.id) }
    }


}

class DoctorsDiff : DiffUtil.ItemCallback<DoctorPOJO>() {
    override fun areItemsTheSame(oldItem: DoctorPOJO, newItem: DoctorPOJO): Boolean {
        return oldItem.doctor?.id == newItem.doctor?.id
    }

    override fun areContentsTheSame(oldItem: DoctorPOJO, newItem: DoctorPOJO): Boolean {
        return oldItem == newItem
    }

}

class DoctorsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = SpecialtyListItemBinding.bind(itemView)

    fun bind(doctor: DoctorPOJO) {
        binding.specialtyName.text = doctor.doctor?.name
        binding.specialtyID.text = doctor.doctor?.id.toString()
    }

}