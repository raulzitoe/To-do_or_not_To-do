package com.group.to_doornotto_do.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.group.to_doornotto_do.repository.ToDoItemListModel
import com.group.to_doornotto_do.repository.ToDoModel
import com.group.to_doornotto_do.repository.ToDoRepository

class ListFragmentViewModel(context: Context, id: Int) : ViewModel() {
    private val repository = ToDoRepository(context)
    var individualList: LiveData<ToDoModel> = repository.getIndividualListData(id)
    var deleteState: MutableLiveData<Boolean> = MutableLiveData()

    init {
        deleteState.value = false
    }

    fun updateList(itemsList: List<ToDoItemListModel>) {
        individualList.value?.let { it ->
            it.itemsList = itemsList.sortedBy { it.isChecked }
            repository.updateList(it)
        }
    }

    fun insertItem(itemName: String) {
        individualList.value?.let { item ->
            var aux = item.itemsList + ToDoItemListModel(itemName, false)
            aux = aux.sortedBy { it.isChecked }
            item.itemsList = aux
            repository.updateList(item)
        }
    }

    fun deleteItem(item: ToDoItemListModel) {
        individualList.value?.let {
            val aux = it.itemsList.toMutableList()
            aux.remove(item)
            it.itemsList = aux
            repository.updateList(it)
        }
    }

    fun toggleDelete() {
        deleteState.value = !(deleteState.value ?: false)
    }
}