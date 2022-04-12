package com.group.to_doornotto_do

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [ToDoModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class ToDoListDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {
        private var instance: ToDoListDatabase? = null

        @Synchronized
        fun getInstance(context: Context): ToDoListDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    context.applicationContext, ToDoListDatabase::class.java,
                    "note_database"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            return instance!!
        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
//                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: ToDoListDatabase) {
            val toDoDao = db.toDoDao()
//            toDoDao.insert(ToDoModel("list 1", listOf()))
//            toDoDao.insert(ToDoModel("list 2", listOf()))
//            toDoDao.insert(ToDoModel("list 3", listOf()))

        }
    }


}