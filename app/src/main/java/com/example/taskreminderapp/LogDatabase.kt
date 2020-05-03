package com.example.taskreminderapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.taskreminderapp.data.model.LogEntryModel

@Database(entities = [LogEntryModel::class], version = 1)
abstract class LogDatabase : RoomDatabase() {

}