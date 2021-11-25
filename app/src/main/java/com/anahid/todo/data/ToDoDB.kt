package com.anahid.todo.data

import android.content.Context
import androidx.room.Room
import com.anahid.todo.data.entity.EntityToDoItem

class ToDoDB(applicationContext: Context) {

    private val db = Room.databaseBuilder(
        applicationContext,
        DBToDo::class.java, "todo_db"
    ).build()

    fun add(toDoItem: EntityToDoItem) {
        db.daoToDo().add(toDoItem)
    }

    fun loadAll(): List<EntityToDoItem> {
        return db.daoToDo().loadAll()
    }

    fun update(toDoItem: EntityToDoItem) {
        db.daoToDo().update(toDoItem)
    }

    fun delete(toDoItem: EntityToDoItem) {
        db.daoToDo().delete(toDoItem)
    }

    fun deleteAll() {
        db.daoToDo().deleteAll()
    }
}
