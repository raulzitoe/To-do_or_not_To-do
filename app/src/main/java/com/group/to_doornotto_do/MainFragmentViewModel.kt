package com.group.to_doornotto_do

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainFragmentViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = ToDoRepository(app)
    private val toDoList = repository.getListData()

    fun insert(listName: ToDoModel) {
        repository.insert(listName)
    }

    fun getListData(): LiveData<List<ToDoModel>> {
        return toDoList
    }
}