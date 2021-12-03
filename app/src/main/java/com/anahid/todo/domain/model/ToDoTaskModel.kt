package com.anahid.todo.domain.model

data class ToDoTaskModel(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Priority,
    val isDone: Boolean,
)

