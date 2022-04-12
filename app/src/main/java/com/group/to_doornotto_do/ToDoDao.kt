package com.group.to_doornotto_do

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ToDoDao {

    @Insert
    fun insert(listName: ToDoModel)

    @Query("Select * from to_do_notes")
    fun gelAllLists(): LiveData<List<ToDoModel>>

    @Update
    fun updateUser(listName: ToDoModel)

    @Delete
    fun deleteUser(listName: ToDoModel)

}