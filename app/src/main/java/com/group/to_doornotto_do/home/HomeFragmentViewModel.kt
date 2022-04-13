package com.group.to_doornotto_do.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.group.to_doornotto_do.ToDoModel
import com.group.to_doornotto_do.ToDoRepository

class HomeFragmentViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = ToDoRepository(app)
    private val toDoList = repository.getListData()

    fun insert(listName: ToDoModel) {
        repository.insert(listName)
    }

    fun getListData(): LiveData<List<ToDoModel>> {
        return toDoList
    }

    fun deleteAll() {
        repository.deleteAll()
    }
}