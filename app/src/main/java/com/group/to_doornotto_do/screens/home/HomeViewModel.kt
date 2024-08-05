package com.group.to_doornotto_do.screens.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.group.to_doornotto_do.model.ToDoModel
import com.group.to_doornotto_do.repository.ToDoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ToDoRepository(application)

    private val _deleteState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val deleteState = _deleteState.asStateFlow()

    private val _state: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState(toDoListsFlow = flowOf()))
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(toDoListsFlow = repository.getListData()) }
    }

    fun onCreateNewList(listName: String) {
        viewModelScope.launch {
            repository.insertNewList(listName)
        }
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