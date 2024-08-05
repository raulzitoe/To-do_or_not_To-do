package com.group.to_doornotto_do.repository

import android.content.Context
import com.group.to_doornotto_do.model.ToDoModel
import kotlinx.coroutines.flow.Flow

class ToDoRepository(appContext: Context) {
    private var database: ToDoDao = ToDoDatabase.getInstance(appContext).toDoDao()

    suspend fun insertNewList(listName: String) {
        database.insert(ToDoModel(listName = listName, itemsList = listOf()))
    }

    fun getListData(): Flow<List<ToDoModel>> {
        return database.gelAllLists()
    }

    suspend fun deleteList(list: ToDoModel) {
        database.deleteList(list)
    }

    suspend fun deleteAll() {
        database.deleteAll()
    }

    fun getIndividualListData(id: Int): Flow<ToDoModel> {
        return database.getIndividualListData(id)
    }

    suspend fun updateList(listModel: ToDoModel) {
        database.updateList(listModel)
    }
}