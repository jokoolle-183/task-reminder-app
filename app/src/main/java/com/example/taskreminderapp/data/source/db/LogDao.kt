package com.example.taskreminderapp.data.source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.taskreminderapp.data.model.LogEntryModel
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface LogDao {

    @Insert
    fun insertLogEntry(logEntryModel: LogEntryModel): Completable

    @Query("SELECT * from logentrymodel")
    fun getLogEntries(): Observable<List<LogEntryModel>>

    @Delete
    fun deleteLogEntry(logEntryModel: LogEntryModel): Completable
}