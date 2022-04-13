package com.group.to_doornotto_do.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.ToDoItemListModel
import com.group.to_doornotto_do.databinding.ItemIndividualListBinding

class ListAdapter (var itemsList: MutableList<ToDoItemListModel> = mutableListOf()): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    private lateinit var binding: ItemIndividualListBinding

    inner class ListViewHolder :RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.itemCheckbox.text = itemsList[position].itemName
            binding.itemCheckbox.isChecked = itemsList[position].isChecked
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        binding = ItemIndividualListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder()
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }


}