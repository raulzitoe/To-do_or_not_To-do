package com.group.to_doornotto_do.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "to_do_notes")
data class ToDoModel (@PrimaryKey(autoGenerate = true) val id: Int=0, val listName: String = "", var itemsList: List<ToDoItemListModel>)


data class ToDoItemListModel (
    var itemName: String,
    var isChecked: Boolean
        )