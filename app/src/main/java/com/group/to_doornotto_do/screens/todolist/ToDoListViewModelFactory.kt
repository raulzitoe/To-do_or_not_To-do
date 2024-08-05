package com.group.to_doornotto_do.screens.todolist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ToDoListViewModelFactory(private val context: Context, private val id: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ToDoListViewModel(context, id) as T
    }
}