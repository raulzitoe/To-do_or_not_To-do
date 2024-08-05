package com.group.to_doornotto_do.repository

import androidx.room.*
import com.group.to_doornotto_do.model.ToDoListModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDao {

    @Insert
    suspend fun insert(listName: ToDoListModel)

    @Query("Select * from to_do_notes")
    fun gelAllLists(): Flow<List<ToDoListModel>>

    @Update
    suspend fun updateList(listName: ToDoListModel)

    @Delete
    suspend fun deleteList(list: ToDoListModel)

    @Query("DELETE from to_do_notes")
    suspend fun deleteAll()

    @Query("SELECT * from to_do_notes WHERE id LIKE :id")
    fun getIndividualListData(id: Int): Flow<ToDoListModel>
}