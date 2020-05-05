package com.example.taskreminderapp

import androidx.lifecycle.ViewModel
import com.example.taskreminderapp.data.model.LogEntryModel
import io.reactivex.Completable
import javax.inject.Inject

class LogViewModel @Inject constructor(private val logRepository: LogRepository) : ViewModel() {

    fun saveLogEntry(logEntryModel: LogEntryModel): Completable {
        return Completable.never()
    }

}