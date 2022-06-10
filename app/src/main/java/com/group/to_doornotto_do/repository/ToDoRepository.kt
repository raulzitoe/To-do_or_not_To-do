package com.group.to_doornotto_do.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class ToDoRepository(appContext: Application) {
    private var database: ToDoDao = ToDoDatabase.getInstance(appContext).toDoDao()

    @WorkerThread
    suspend fun insert(listName: ToDoModel) {
        database.insert(listName)
    }

    @WorkerThread
    fun getListData(): LiveData<List<ToDoModel>> {
        return database.gelAllLists()
    }

    @WorkerThread
    suspend fun deleteList(list: ToDoModel) {
        database.deleteList(list)
    }

    @WorkerThread
    suspend fun deleteAll() {
        database.deleteAll()
    }

    @WorkerThread
    fun getIndividualListData(id: Int): LiveData<ToDoModel> {
        return database.getIndividualListData(id)
    }

    @WorkerThread
    suspend fun updateList(listModel: ToDoModel) {
        database.updateList(listModel)
    }
}