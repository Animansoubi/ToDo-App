package com.anahid.todo.domain

import com.anahid.todo.data.ToDoDao
import com.anahid.todo.domain.map.ToDoMapper.toEntity
import com.anahid.todo.domain.map.ToDoMapper.toModel
import com.anahid.todo.domain.model.ToDoTaskModel

class ToDoRepository(private val toDoDao: ToDoDao) {

    private val cache: MutableList<ToDoTaskModel> = mutableListOf()

    fun add(toDoTaskModel: ToDoTaskModel) {
        cache.add(toDoTaskModel)
        toDoDao.add(toDoTaskModel.toEntity())
    }

    fun loadAll(): List<ToDoTaskModel> {
        if (cache.isEmpty()) {
            cache.addAll(toDoDao.loadAll().map { it.toModel() })
        }

        return cache.toList()
    }

    fun update(toDoTaskModel: ToDoTaskModel) {
        cache.remove(toDoTaskModel)
        cache.add(toDoTaskModel)
        toDoDao.update(toDoTaskModel.toEntity())
    }

    fun getSortedTaskByLowestPriority(): List<ToDoTaskModel> {
        return toDoDao.getSortedTaskByLowestPriority().map { it.toModel() }
    }

    fun getSortedTaskByHighestPriority(): List<ToDoTaskModel> {
        return toDoDao.getSortedTaskByHighestPriority().map { it.toModel() }
    }

    fun getTaskBySearchPhrase(searchPhrase: String): List<ToDoTaskModel> {
        return toDoDao.getTaskBySearchPhrase(searchPhrase).map { it.toModel() }
    }

    fun delete(toDoTaskModel: ToDoTaskModel) {
        cache.remove(toDoTaskModel)
        toDoDao.delete(toDoTaskModel.toEntity())
    }

    fun deleteAll() {
        cache.clear()
        toDoDao.deleteAll()
    }
}