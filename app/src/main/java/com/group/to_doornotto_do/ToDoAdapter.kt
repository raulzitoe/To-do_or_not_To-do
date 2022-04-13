package com.group.to_doornotto_do

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.databinding.ItemToDoListsBinding

class ToDoAdapter(var toDoList: List<ToDoModel> = listOf(), val listener: ToDoItemListener) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    private lateinit var binding: ItemToDoListsBinding

    inner class ToDoViewHolder() :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.listName.text = toDoList[position].listName
            binding.root.setOnClickListener {
                listener.itemClick(toDoList[position].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        binding =
            ItemToDoListsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder()
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return toDoList.size
    }

    interface ToDoItemListener {
        fun itemClick(id: Int)
    }
}