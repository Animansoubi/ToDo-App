package com.anahid.todo.domain.model

data class ModelToDoItem(
    val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean,
)
