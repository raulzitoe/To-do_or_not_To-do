package com.group.to_doornotto_do.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.group.to_doornotto_do.repository.ToDoModel
import com.group.to_doornotto_do.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ToDoRepository(application)
    private val toDoList =  repository.getListData()

    private val _deleteState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val deleteState = _deleteState.asStateFlow()

    fun insert(listName: ToDoModel) {
        viewModelScope.launch {
            repository.insert(listName)
        }
    }

    fun getListData(): Flow<List<ToDoModel>> {
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
        _deleteState.value = !_deleteState.value
    }
}