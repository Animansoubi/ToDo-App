package com.anahid.todo.domain.map

import com.anahid.todo.data.entity.EntityToDoItem
import com.anahid.todo.domain.model.ModelToDoItem

object ToDoMapper {

    fun ModelToDoItem.toEntity(): EntityToDoItem =
        EntityToDoItem(
            id = id,
            title = title,
            description = description,
            isDone = if (isDone) 1 else 0
        )

    fun EntityToDoItem.toModel(): ModelToDoItem =
        ModelToDoItem(
            id = id,
            title = title,
            description = description,
            isDone = isDone == 1
        )
}