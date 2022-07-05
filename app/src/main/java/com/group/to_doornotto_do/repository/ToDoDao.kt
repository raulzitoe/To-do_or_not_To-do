package com.group.to_doornotto_do.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ToDoDao {

    @Insert
    suspend fun insert(listName: ToDoModel)

    @Query("Select * from to_do_notes")
    fun gelAllLists(): Flow<List<ToDoModel>>

    @Update
    suspend fun updateList(listName: ToDoModel)

    @Delete
    suspend fun deleteList(list: ToDoModel)

    @Query("DELETE from to_do_notes")
    suspend fun deleteAll()

    @Query("SELECT * from to_do_notes WHERE id LIKE :id")
    suspend fun getIndividualListData(id: Int): ToDoModel

}