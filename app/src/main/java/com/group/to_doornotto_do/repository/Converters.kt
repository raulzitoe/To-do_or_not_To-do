package com.group.to_doornotto_do.repository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromString(value: String): List<ToDoItemListModel> {
        val listType = object : TypeToken<List<ToDoItemListModel>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<ToDoItemListModel>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}