package com.group.to_doornotto_do.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.R
import com.group.to_doornotto_do.databinding.ItemRecyclerHomeBinding
import com.group.to_doornotto_do.repository.ToDoModel


class HomeAdapter(val listener: ToDoItemListener) :
    ListAdapter<ToDoModel, HomeAdapter.ToDoViewHolder>(ToDoDiffCallback()) {
    var deleteState: Boolean = false

    inner class ToDoViewHolder(private val binding: ItemRecyclerHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ToDoModel) {
            binding.btnListDelete.isVisible = deleteState
            binding.btnListDelete.setOnClickListener(null)
            if (deleteState) {
                binding.btnListDelete.setOnClickListener {
                    listener.deleteList(item)
                }
            }

            binding.listName.text = item.listName
            binding.root.setOnClickListener {
                listener.itemClick(item.id)
            }
            with(binding.listItemQuantity) {
                val quantityText = this.context.getString(
                    R.string.this_list_has_x_items,
                    item.itemsList.size
                )
                this.text = quantityText
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding =
            ItemRecyclerHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    interface ToDoItemListener {
        fun itemClick(id: Int)
        fun deleteList(list: ToDoModel)
    }
}

class ToDoDiffCallback : DiffUtil.ItemCallback<ToDoModel>() {
    override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
        return oldItem == newItem
    }

}