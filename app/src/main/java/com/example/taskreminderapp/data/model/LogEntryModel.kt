package com.example.taskreminderapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class LogEntryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val type: LogType? = null
)

enum class LogType(val value: String) {
    TASK("task"),
    EVENT("event");

    override fun toString(): String {
        return value.toLowerCase(Locale.getDefault())
    }
}
