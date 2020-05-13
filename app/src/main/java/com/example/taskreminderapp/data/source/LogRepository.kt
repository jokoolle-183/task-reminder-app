package com.example.taskreminderapp.data.source

import android.util.Log
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.source.db.LogDatabase
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LogRepository @Inject constructor(private val logDb: LogDatabase) {

    init {
        Log.d("LogRepo", "I'm alive")
    }

    fun saveLogEntry(logEntryModel: LogEntryModel): Completable {
        return logDb.getLogDao().insertLogEntry(logEntryModel)
    }

    fun getLogEntryList(): Observable<List<LogEntryModel>> {
        return logDb.getLogDao().getLogEntries()
    }

    fun deleteLogEntry(logEntry: LogEntryModel): Completable {
        return logDb.getLogDao().deleteLogEntry(logEntry)
    }
}