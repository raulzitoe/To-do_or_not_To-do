package com.group.to_doornotto_do.list

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.group.to_doornotto_do.databinding.ItemRecyclerListBinding
import com.group.to_doornotto_do.model.ToDoItemListModel


class ItemListAdapter(
    val listener: ListAdapterListener
) : ListAdapter<ToDoItemListModel, ItemListAdapter.ListViewHolder>(ToDoItemDiffCallback()) {
    var deleteState: Boolean = false

    inner class ListViewHolder(private val binding: ItemRecyclerListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ToDoItemListModel) {
            val checkBox = binding.itemCheckbox

            binding.btnItemDelete.isVisible = deleteState
            binding.btnItemDelete.setOnClickListener(null)
            if (deleteState) {
                binding.btnItemDelete.setOnClickListener {
                    listener.deleteItem(item)
                }
            }

            with(checkBox) {
                this.setOnCheckedChangeListener(null)
                this.text = item.itemName
                this.isChecked = item.isChecked
                this.setOnCheckedChangeListener { _, isChecked ->
                    item.isChecked = isChecked
                    listener.itemChecked()
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
        val item = getItem(position)
        holder.bind(item)
    }

    interface ListAdapterListener {
        fun itemChecked()
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

class ToDoItemDiffCallback : DiffUtil.ItemCallback<ToDoItemListModel>() {
    override fun areItemsTheSame(oldItem: ToDoItemListModel, newItem: ToDoItemListModel): Boolean {
        return oldItem.itemID == newItem.itemID
    }

    override fun areContentsTheSame(
        oldItem: ToDoItemListModel,
        newItem: ToDoItemListModel
    ): Boolean {
        return oldItem.itemName == newItem.itemName
    }
}