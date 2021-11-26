package com.anahid.todo.data

import androidx.room.*
import com.anahid.todo.data.entity.EntityToDoItem

@Dao
interface DaoToDo {

    @Insert
    fun add(toDoItem: EntityToDoItem)

    @Query("SELECT * FROM todo_table")
    fun loadAll(): List<EntityToDoItem>

    @Update
    fun update(toDoItem: EntityToDoItem)

    @Delete
    fun delete(toDoItem: EntityToDoItem)

    @Query("DELETE FROM todo_table")
    fun deleteAll()
}