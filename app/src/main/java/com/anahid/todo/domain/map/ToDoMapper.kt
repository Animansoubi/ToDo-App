package com.anahid.todo.domain.map

import com.anahid.todo.data.entity.EntityToDoItem
import com.anahid.todo.domain.model.Priority
import com.anahid.todo.domain.model.ToDoTaskModel

object ToDoMapper {

    fun ToDoTaskModel.toEntity(): EntityToDoItem =
        EntityToDoItem(
            id = id,
            title = title,
            description = description,
            priority = priority.value,
            isDone = if (isDone) 1 else 0
        )

    fun EntityToDoItem.toModel(): ToDoTaskModel =
        ToDoTaskModel(
            id = id,
            title = title,
            description = description,
            priority = Priority.from(priority),
            isDone = isDone == 1
        )
}