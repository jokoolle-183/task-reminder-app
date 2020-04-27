package com.example.taskreminderapp.data.model

data class LogEntryModel(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val type: LogType? = null
)

enum class LogType {
    TASK,
    EVENT
}
