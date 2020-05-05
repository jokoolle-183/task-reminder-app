package com.example.taskreminderapp

import com.example.taskreminderapp.data.LogDatabase
import com.example.taskreminderapp.data.model.LogEntryModel
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class LogRepository @Inject constructor(private val logDb: LogDatabase) {

    fun saveLogEntry(logEntryModel: LogEntryModel): Completable {
        return logDb.getLogDao().insertLog(logEntryModel)
    }

    fun getLogEntryList(): Observable<List<LogEntryModel>> {
        return logDb.getLogDao().getLogs()
    }
}