package com.group.to_doornotto_do.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.group.to_doornotto_do.databinding.ItemRecyclerListBinding
import com.group.to_doornotto_do.repository.ToDoItemListModel


class ListAdapter(
    var itemsList: MutableList<ToDoItemListModel> = mutableListOf(),
    val listener: ListAdapterListener
) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {
    var deleteState: Boolean = false

    inner class ListViewHolder(private val binding: ItemRecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val checkBox = binding.itemCheckbox

            binding.btnItemDelete.isVisible = deleteState
            binding.btnItemDelete.setOnClickListener(null)
            if (deleteState) {
                binding.btnItemDelete.setOnClickListener {
                    listener.deleteItem(itemsList[position])
                }
            }

            with(checkBox) {
                this.setOnCheckedChangeListener(null)
                this.text = itemsList[position].itemName
                this.isChecked = itemsList[position].isChecked
                this.setOnCheckedChangeListener { _, isChecked ->
                    itemsList[position].isChecked = isChecked
                    listener.itemChecked(itemsList)
                    strikeThroughText(checkBox)
                }
            }
            strikeThroughText(checkBox)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRecyclerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    interface ListAdapterListener {
        fun itemChecked(itemsList: List<ToDoItemListModel>)
        fun deleteItem(item: ToDoItemListModel)
    }

    private fun strikeThroughText(item: MaterialCheckBox) {
        if (item.isChecked) {
            item.paintFlags = item.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            item.paintFlags = item.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }
}