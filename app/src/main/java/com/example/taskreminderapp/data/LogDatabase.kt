package com.example.taskreminderapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskreminderapp.Converters
import com.example.taskreminderapp.data.model.LogEntryModel

@Database(entities = [LogEntryModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class LogDatabase : RoomDatabase() {
    abstract fun getLogDao(): LogDao
}