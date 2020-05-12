package com.example.taskreminderapp

import androidx.lifecycle.ViewModel
import com.example.taskreminderapp.data.model.LogEntryModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LogViewModel @Inject constructor(private val logRepository: LogRepository) : ViewModel() {

    fun saveLogEntry(logEntryModel: LogEntryModel): Completable {
        return logRepository.saveLogEntry(logEntryModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getLogList(): Observable<List<LogEntryModel>> {
        return logRepository.getLogEntryList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}