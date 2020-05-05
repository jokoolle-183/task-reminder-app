package com.example.taskreminderapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.taskreminderapp.data.model.LogEntryModel
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface LogDao {

    @Insert
    fun insertLog(logEntryModel: LogEntryModel): Completable

    @Query("SELECT * from logentrymodel")
    fun getLogs(): Observable<List<LogEntryModel>>
}