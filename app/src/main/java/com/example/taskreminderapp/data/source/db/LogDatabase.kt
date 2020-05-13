package com.example.taskreminderapp.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.utils.Converters

@Database(entities = [LogEntryModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class LogDatabase : RoomDatabase() {
    abstract fun getLogDao(): LogDao
}