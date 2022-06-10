package com.group.to_doornotto_do.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.group.to_doornotto_do.repository.ToDoModel
import com.group.to_doornotto_do.repository.ToDoRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val repository = ToDoRepository(appContext)
    private val toDoList =  repository.getListData()
    var deleteState: MutableLiveData<Boolean> = MutableLiveData()

    fun insert(listName: ToDoModel) {
        viewModelScope.launch {
            repository.insert(listName)
        }
    }

    fun getListData(): LiveData<List<ToDoModel>> {
        return toDoList
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun deleteList(list: ToDoModel) {
        viewModelScope.launch {
            repository.deleteList(list)
        }
    }

    fun toggleDelete() {
        deleteState.value = !(deleteState.value ?: false)
    }
}