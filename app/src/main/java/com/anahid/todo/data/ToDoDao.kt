package com.anahid.todo.data

import androidx.room.*
import com.anahid.todo.data.entity.EntityToDoItem

@Dao
interface ToDoDao {

    @Insert
    fun add(toDoItem: EntityToDoItem)

    @Query("SELECT * FROM todo_table")
    fun loadAll(): List<EntityToDoItem>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 3 END")
    fun getSortedTaskByLowestPriority(): List<EntityToDoItem>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 3 WHEN priority LIKE 'M%' THEN 2 WHEN priority LIKE 'H%' THEN 1 END")
    fun getSortedTaskByHighestPriority(): List<EntityToDoItem>

    @Query("SELECT * FROM todo_table WHERE title LIKE '%' || :searchPhrase || '%' OR [description] LIKE '%' || :searchPhrase || '%'")
    fun getTaskBySearchPhrase(searchPhrase: String): List<EntityToDoItem>

    @Update
    fun update(toDoItem: EntityToDoItem)

    @Delete
    fun delete(toDoItem: EntityToDoItem)

    @Query("DELETE FROM todo_table")
    fun deleteAll()
}