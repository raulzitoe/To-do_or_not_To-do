package com.group.to_doornotto_do.screens.todolist

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.group.to_doornotto_do.model.ToDoItemListModel
import com.group.to_doornotto_do.model.ToDoModel
import com.group.to_doornotto_do.repository.ToDoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ToDoListViewModel(context: Context, id: Int) : AndroidViewModel(context.applicationContext as Application) {
    private val repository = ToDoRepository(context)

    private val _individualList: MutableStateFlow<ToDoModel> = MutableStateFlow(ToDoModel(itemsList = listOf()))
    val individualList = _individualList.asStateFlow()

    private val _deleteState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val deleteState = _deleteState.asStateFlow()

    private val _state: MutableStateFlow<ToDoListUIState> = MutableStateFlow(ToDoListUIState(toDoItemsFlow = flowOf()))
    val state = _state.asStateFlow()

    init {
        _state.update { it.copy(toDoItemsFlow = repository.getIndividualListData(id)) }
    }

    fun updateList() {
        val auxModel = _individualList.value.copy()
        val auxList = auxModel.itemsList.toMutableList()
        auxModel.itemsList = auxList.sortedBy { it.isChecked }
        _individualList.value = auxModel
        viewModelScope.launch {
            repository.updateList(auxModel)
        }
    }

    fun insertItem(itemName: String) {
        val auxModel = _individualList.value.copy()
        val auxList = auxModel.itemsList.toMutableList()
        val id = auxList.size + 1
        auxList.add(ToDoItemListModel(itemID = id, itemName = itemName, isChecked = false))
        auxModel.itemsList = auxList
        _individualList.value = auxModel
        viewModelScope.launch {
            repository.updateList(auxModel)
        }
    }

    fun deleteItem(item: ToDoItemListModel) {
        val auxModel = _individualList.value.copy()
        val auxList = auxModel.itemsList.toMutableList()
        auxList.remove(item)
        auxModel.itemsList = auxList
        _individualList.value = auxModel
        viewModelScope.launch {
            repository.updateList(auxModel)
        }
    }

    fun toggleDelete() {
        _deleteState.value = !_deleteState.value
    }

    fun onAddItem(toDoList: ToDoModel, newItem: String) {
        viewModelScope.launch {
            val oldItems = toDoList.itemsList
            repository.updateList(
                toDoList.copy(
                    itemsList = oldItems.plus(
                        ToDoItemListModel(
                            itemID = oldItems.size + 1,
                            itemName = newItem,
                            isChecked = false
                        )
                    )
                )
            )
        }
    }

    fun onCheckedChange(toDoList: ToDoModel, itemId: Int) {
        viewModelScope.launch {
            val newItems = toDoList.itemsList.map {
                if (it.itemID == itemId) {
                    it.copy(isChecked = !it.isChecked)
                } else {
                    it
                }
            }

            repository.updateList(toDoList.copy(itemsList = newItems))
        }
    }
}