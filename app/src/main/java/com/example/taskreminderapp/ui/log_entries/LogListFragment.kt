package com.example.taskreminderapp.ui.log_entries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskreminderapp.R
import com.example.taskreminderapp.data.model.LogEntryModel
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_log_list.*
import kotlinx.android.synthetic.main.fragment_log_list.view.*
import javax.inject.Inject

class LogListFragment : DaggerFragment(), LogListAdapter.OnDeleteClickListener {

    private lateinit var rvLogList: RecyclerView
    private lateinit var logListAdapter: LogListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var logListViewModel: LogListViewModel

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_list, container, false).apply {
            logListAdapter = LogListAdapter(this@LogListFragment)

            rvLogList = recyclerView
            rvLogList.adapter = logListAdapter
            rvLogList.layoutManager = LinearLayoutManager(context)

            fabAddTask.setOnClickListener {
                findNavController().navigate(R.id.action_logListFragment_to_logDetailFragment)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logListViewModel =
            ViewModelProvider(this, viewModelFactory).get(LogListViewModel::class.java)

        logListViewModel.getLogList()
            .subscribeBy(
                onNext = { logList ->
                    if (logList.isEmpty()) {
                        populateLogList(logList)
                        renderNoTasksMessage()
                    } else {
                        populateLogList(logList)
                    }
                },
                onError = {
                    Log.d("LogListFragment", "Error displaying tasks: $it")
                }
            ).apply {
                compositeDisposable.add(this)
            }
    }

    private fun renderNoTasksMessage() {
        noTasksMessage.visibility = View.VISIBLE
    }

    private fun populateLogList(list: List<LogEntryModel>) {
        noTasksMessage.visibility = View.GONE
        logListAdapter.loadList(list)
    }

    override fun onDeleteClick(logEntry: LogEntryModel) {
        logListViewModel.deleteLogEntry(logEntry).subscribeBy(
            onComplete = {
                Toast.makeText(context, "Entry deleted!", Toast.LENGTH_SHORT).show()
            },
            onError = {
                Log.d("OnDeleteClick", "Error deleting entry: $it")
                Toast.makeText(context, "Failed to delete entry.", Toast.LENGTH_SHORT).show()
            }
        ).apply {
            compositeDisposable.add(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}