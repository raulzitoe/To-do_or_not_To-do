package com.group.to_doornotto_do.repository

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ToDoDao {

    @Insert
    suspend fun insert(listName: ToDoModel)

    @Query("Select * from to_do_notes")
    fun gelAllLists(): LiveData<List<ToDoModel>>

    @Update
    suspend fun updateList(listName: ToDoModel)

    @Delete
    suspend fun deleteList(list: ToDoModel)

    @Query("DELETE from to_do_notes")
    suspend fun deleteAll()

    @Query("SELECT * from to_do_notes WHERE id LIKE :id")
    fun getIndividualListData(id: Int): LiveData<ToDoModel>

}