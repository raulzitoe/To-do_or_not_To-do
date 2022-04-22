package com.group.to_doornotto_do.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.group.to_doornotto_do.repository.ToDoModel
import com.group.to_doornotto_do.repository.ToDoRepository

class HomeFragmentViewModel(app: Application) : AndroidViewModel(app) {
    private val repository = ToDoRepository(app)
    private val toDoList = repository.getListData()
    var deleteState: MutableLiveData<Boolean> = MutableLiveData()

    fun insert(listName: ToDoModel) {
        repository.insert(listName)
    }

    fun getListData(): LiveData<List<ToDoModel>> {
        return toDoList
    }

    fun deleteAll() {
        repository.deleteAll()
    }

    fun deleteList(list: ToDoModel) {
        repository.deleteList(list)
    }

    fun toggleDelete() {
        deleteState.value = !(deleteState.value ?: false)
    }
}