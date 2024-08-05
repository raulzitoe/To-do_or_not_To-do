package com.group.to_doornotto_do.repository

import android.content.Context
import com.group.to_doornotto_do.model.ToDoListModel
import kotlinx.coroutines.flow.Flow

class ToDoRepository(appContext: Context) {
    private var database: ToDoDao = ToDoDatabase.getInstance(appContext).toDoDao()

    suspend fun insertNewList(listName: String) {
        database.insert(ToDoListModel(listName = listName, itemsList = listOf()))
    }

    fun getListData(): Flow<List<ToDoListModel>> {
        return database.gelAllLists()
    }

    suspend fun deleteList(list: ToDoListModel) {
        database.deleteList(list)
    }

    suspend fun deleteAll() {
        database.deleteAll()
    }

    fun getIndividualListData(id: Int): Flow<ToDoListModel> {
        return database.getIndividualListData(id)
    }

    suspend fun updateList(listModel: ToDoListModel) {
        database.updateList(listModel)
    }
}