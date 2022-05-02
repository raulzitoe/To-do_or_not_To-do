package com.group.to_doornotto_do.repository

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ToDoModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {
        private var instance: ToDoDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ToDoDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, ToDoDatabase::class.java,
                    "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}