package com.anahid.todo.domain

import com.anahid.todo.data.DaoToDo
import com.anahid.todo.domain.map.ToDoMapper.toEntity
import com.anahid.todo.domain.map.ToDoMapper.toModel
import com.anahid.todo.domain.model.ModelToDoItem

class ToDoRepository(private val daoToDo: DaoToDo) {

    private val cache: MutableList<ModelToDoItem> = mutableListOf()

    fun add(toDoItem: ModelToDoItem) {
        cache.add(toDoItem)
        daoToDo.add(toDoItem.toEntity())
    }

    fun loadAll(): List<ModelToDoItem> {
        if (cache.isEmpty()) {
            cache.addAll(daoToDo.loadAll().map { it.toModel() })
        }

        return cache.toList()
    }

    fun update(toDoItem: ModelToDoItem) {
        cache.remove(toDoItem)
        cache.add(toDoItem)

        daoToDo.update(toDoItem.toEntity())
    }

    fun delete(toDoItem: ModelToDoItem) {
        cache.remove(toDoItem)
        daoToDo.delete(toDoItem.toEntity())
    }

    fun deleteAll() {
        cache.clear()
        daoToDo.deleteAll()
    }
}