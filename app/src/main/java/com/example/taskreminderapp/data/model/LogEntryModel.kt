package com.example.taskreminderapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LogEntryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val type: LogType? = null
)

enum class LogType(value: String) {
    TASK("task"),
    EVENT("event");
}
