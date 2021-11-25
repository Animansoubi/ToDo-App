package com.anahid.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anahid.todo.data.entity.EntityToDoItem

@Database(entities = [EntityToDoItem::class], version = 1)
abstract class DBToDo : RoomDatabase() {
    abstract fun daoToDo(): DaoToDo
}
