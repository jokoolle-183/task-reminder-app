package com.example.taskreminderapp.ui.log_entries

import androidx.lifecycle.ViewModel
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.source.LogRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LogListViewModel @Inject constructor(private val logRepository: LogRepository) : ViewModel() {

    fun getLogList(): Observable<List<LogEntryModel>> {
        return logRepository.getLogEntryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteLogEntry(logEntry: LogEntryModel): Completable {
        return logRepository.deleteLogEntry(logEntry)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}