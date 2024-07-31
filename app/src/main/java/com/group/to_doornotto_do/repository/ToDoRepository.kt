package com.group.to_doornotto_do.repository

import android.app.Application
import com.group.to_doornotto_do.model.ToDoModel
import kotlinx.coroutines.flow.Flow

class ToDoRepository(appContext: Application) {
    private var database: ToDoDao = ToDoDatabase.getInstance(appContext).toDoDao()

    suspend fun insert(listName: ToDoModel) {
        database.insert(listName)
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

    suspend fun getIndividualListData(id: Int): ToDoModel {
        return database.getIndividualListData(id)
    }

    suspend fun updateList(listModel: ToDoModel) {
        database.updateList(listModel)
    }
}