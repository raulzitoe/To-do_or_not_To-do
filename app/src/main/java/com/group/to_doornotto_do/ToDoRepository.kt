package com.group.to_doornotto_do

import android.content.Context
import androidx.lifecycle.LiveData

class ToDoRepository(context: Context) {
    private var database: ToDoDao = ToDoListDatabase.getInstance(context).toDoDao()

    fun insert(listName: ToDoModel) {
        database.insert(listName)
    }

    fun getListData(): LiveData<List<ToDoModel>> {
        return database.gelAllLists()
    }

    fun deleteList(list: ToDoModel) {
        database.deleteList(list)
    }

    fun deleteAll() {
        database.deleteAll()
    }

    fun getIndividualListData(id: Int): LiveData<ToDoModel> {
        return database.getIndividualListData(id)
    }

    fun updateList(listModel: ToDoModel) {
        database.updateList(listModel)
    }

}