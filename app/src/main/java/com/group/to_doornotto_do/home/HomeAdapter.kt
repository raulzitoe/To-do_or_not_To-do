package com.group.to_doornotto_do.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.R
import com.group.to_doornotto_do.databinding.ItemRecyclerHomeBinding
import com.group.to_doornotto_do.repository.ToDoModel


class HomeAdapter(var toDoList: List<ToDoModel> = listOf(), val listener: ToDoItemListener) :
    RecyclerView.Adapter<HomeAdapter.ToDoViewHolder>() {
    var deleteState: Boolean = false

    inner class ToDoViewHolder(private val binding: ItemRecyclerHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.btnListDelete.isVisible = deleteState
            binding.btnListDelete.setOnClickListener(null)
            if (deleteState) {
                binding.btnListDelete.setOnClickListener {
                    listener.deleteList(toDoList[position])
                }
            }

            binding.listName.text = toDoList[position].listName
            binding.root.setOnClickListener {
                listener.itemClick(toDoList[position].id)
            }
            with(binding.listItemQuantity) {
                val quantityText = this.context.getString(
                    R.string.this_list_has_x_items,
                    toDoList[position].itemsList.size
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
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    interface ToDoItemListener {
        fun itemClick(id: Int)
        fun deleteList(list: ToDoModel)
    }
}