package com.anahid.todo.domain.model

sealed class Priority(val value: String) {
    object High : Priority(HIGH)
    object Medium : Priority(MEDIUM)
    object Low : Priority(LOW)

    companion object {
        private const val HIGH = "high"
        private const val MEDIUM = "medium"
        private const val LOW = "low"

        fun from(value: String): Priority {
            return when (value) {
                HIGH -> High
                MEDIUM -> Medium
                LOW -> Low
                else -> Low
            }
        }
    }
}

