package com.klemer.klinicalsys.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.klemer.klinicalsys.R
import com.klemer.klinicalsys.databinding.SpecialtyListItemBinding
import com.klemer.klinicalsys.model.Specialty

class SpecialtiesAdapter(private val itemClick: (Int) -> Unit) :
    ListAdapter<Specialty, SpecialtiesVH>(SpecialtiesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialtiesVH {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.specialty_list_item, parent, false).apply {
                return SpecialtiesVH(this)
            }
    }

    override fun onBindViewHolder(holder: SpecialtiesVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener { itemClick(item.id) }
    }


}

class SpecialtiesDiff : DiffUtil.ItemCallback<Specialty>() {
    override fun areItemsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Specialty, newItem: Specialty): Boolean {
        return oldItem == newItem
    }
}

class SpecialtiesVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = SpecialtyListItemBinding.bind(itemView)

    fun bind(specialty: Specialty) {
        binding.specialtyName.text = specialty.name
    }

}