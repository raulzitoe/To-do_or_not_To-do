package com.group.to_doornotto_do

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do_notes")
data class ToDoModel (@PrimaryKey(autoGenerate = true) val id: Int=0, val listName: String, val itemsList: List<ToDoItemListModel>)


data class ToDoItemListModel (
    val itemName: String,
    val isChecked: Boolean
        )