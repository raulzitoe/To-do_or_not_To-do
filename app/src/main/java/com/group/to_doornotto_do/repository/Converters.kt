package com.group.to_doornotto_do.repository

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.group.to_doornotto_do.model.ToDoItemModel

class Converters {

    @TypeConverter
    fun fromString(value: String): List<ToDoItemModel> {
        val listType = object : TypeToken<List<ToDoItemModel>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<ToDoItemModel>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}