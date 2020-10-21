package com.example.taskreminderapp.ui.add_log_entry

import androidx.lifecycle.ViewModel
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.source.LogRepository
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddLogEntryViewModel @Inject constructor(private val logRepository: LogRepository) :
    ViewModel() {

    fun saveLogEntry(logEntryModel: LogEntryModel): Completable {
        return logRepository.saveLogEntry(logEntryModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}