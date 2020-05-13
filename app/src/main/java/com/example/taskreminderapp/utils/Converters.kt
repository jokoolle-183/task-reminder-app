package com.example.taskreminderapp.utils

import androidx.room.TypeConverter
import com.example.taskreminderapp.data.model.LogType

class Converters {

    @TypeConverter
    fun toLogType(value: String) = enumValueOf<LogType>(value)

    @TypeConverter
    fun fromLogType(value: LogType) = value.name
}
