package com.group.to_doornotto_do.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.group.to_doornotto_do.repository.ToDoItemListModel
import com.group.to_doornotto_do.repository.ToDoModel
import com.group.to_doornotto_do.repository.ToDoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListFragmentViewModel(application: Application, id: Int) : AndroidViewModel(application) {
    private val repository = ToDoRepository(application)

    private val _individualList: MutableStateFlow<ToDoModel> = MutableStateFlow(ToDoModel(itemsList = listOf()))
    val individualList = _individualList.asStateFlow()

    private val _deleteState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val deleteState = _deleteState.asStateFlow()

    init {
        viewModelScope.launch {
            _individualList.value = repository.getIndividualListData(id)
        }
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
}