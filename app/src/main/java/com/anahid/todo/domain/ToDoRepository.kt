package com.anahid.todo.domain

import android.content.Context
import com.anahid.todo.data.ToDoDB
import com.anahid.todo.domain.map.ToDoMapper.toEntity
import com.anahid.todo.domain.map.ToDoMapper.toModel
import com.anahid.todo.domain.model.ModelToDoItem

class ToDoRepository(applicationContext: Context) {

    private val cache: MutableList<ModelToDoItem> = mutableListOf()
    private val db = ToDoDB(applicationContext)

    fun add(toDoItem: ModelToDoItem) {
        cache.add(toDoItem)
        db.add(toDoItem.toEntity())
    }

    fun loadAll(): List<ModelToDoItem> {
        if (cache.isEmpty()) {
            cache.addAll(db.loadAll().map { it.toModel() })
        }

        return cache.toList()
    }

    fun update(toDoItem: ModelToDoItem) {
        cache.remove(toDoItem)
        cache.add(toDoItem)

        db.update(toDoItem.toEntity())
    }

    fun delete(toDoItem: ModelToDoItem) {
        cache.remove(toDoItem)
        db.delete(toDoItem.toEntity())
    }

    fun deleteAll() {
        cache.clear()
        db.deleteAll()
    }
}