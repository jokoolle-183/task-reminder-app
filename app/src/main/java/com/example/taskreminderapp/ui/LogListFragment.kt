package com.example.taskreminderapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskreminderapp.LogViewModel
import com.example.taskreminderapp.R
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.model.LogType
import com.example.taskreminderapp.ui.adapter.LogListAdapter
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.fragment_log_list.*
import kotlinx.android.synthetic.main.fragment_log_list.view.*
import javax.inject.Inject

class LogListFragment : DaggerFragment() {

    private lateinit var rvLogList: RecyclerView
    private lateinit var logListAdapter: LogListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var logViewModel: LogViewModel

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log_list, container, false).apply {
            logListAdapter = LogListAdapter()

            rvLogList = recyclerView
            rvLogList.adapter = logListAdapter
            rvLogList.layoutManager = LinearLayoutManager(context)


            populateAdapter()
            fabAddTask.setOnClickListener {
                findNavController().navigate(R.id.action_logListFragment_to_logDetailFragment)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        logViewModel = ViewModelProvider(this, viewModelFactory).get(LogViewModel::class.java)

        logViewModel.getLogList()
            .subscribeBy(
                onNext = { logList ->
                    if (logList.isNotEmpty()) {
                        displayLogList(logList)
                    } else {
                        renderNoTasksMessage()
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

    private fun displayLogList(list: List<LogEntryModel>) {
        noTasksMessage.visibility = View.GONE
        logListAdapter.loadList(list)
    }

    private fun populateAdapter() {
        val list = arrayListOf(
            LogEntryModel(title = "Cestitaj Mikici rodjendan", type = LogType.TASK),
            LogEntryModel(title = "Reci da je volis", type = LogType.TASK),
            LogEntryModel(title = "Ljubaaav", type = LogType.EVENT)
        )
        logListAdapter.loadList(list)
    }
}