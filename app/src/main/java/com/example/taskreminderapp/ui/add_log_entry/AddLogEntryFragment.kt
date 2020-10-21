package com.example.taskreminderapp.ui.add_log_entry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taskreminderapp.R
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.model.LogType
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_log_detail.*
import java.util.*
import javax.inject.Inject

class AddLogEntryFragment : DaggerFragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var addLogEntryViewModel: AddLogEntryViewModel

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let { safeContext ->
            ArrayAdapter.createFromResource(
                safeContext,
                R.array.type_array,
                android.R.layout.simple_spinner_item
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = this
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        addLogEntryViewModel =
            ViewModelProvider(
                requireActivity(),
                viewModelFactory

            ).get(AddLogEntryViewModel::class.java)

        fabSave.setOnClickListener {
            val logType =
                getLogType(spinner.selectedItem.toString().toLowerCase(Locale.getDefault()))

            addLogEntryViewModel.saveLogEntry(
                LogEntryModel(
                    title = etTitle.text.toString(),
                    type = logType
                )
            ).subscribeBy(
                onComplete = {
                    Toast.makeText(context, "Log entry saved!", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                },
                onError = {
                    Log.d("LogDetailFragment", "Error saving entry: $it")
                    Toast.makeText(
                        context,
                        "Failed to save the log entry, sorry.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            ).apply {
                    compositeDisposable.add(this)
                }
        }
    }

    private fun getLogType(selectedItem: String): LogType {
        return when (selectedItem) {
            LogType.TASK.toString() -> {
                LogType.TASK
            }
            else -> {
                LogType.EVENT
            }
        }
    }
}