package com.example.taskreminderapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskreminderapp.R
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.model.LogType
import com.example.taskreminderapp.ui.adapter.LogListAdapter
import kotlinx.android.synthetic.main.fragment_log.view.*

class LogFragment : Fragment() {

    private lateinit var rvLogList: RecyclerView
    private lateinit var logListAdapter: LogListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_log, container, false).apply {
            logListAdapter = LogListAdapter()

            rvLogList = recyclerView
            rvLogList.adapter = logListAdapter
            rvLogList.layoutManager = LinearLayoutManager(context)

            populateAdapter()
        }
    }

    private fun populateAdapter() {
        val list = arrayListOf<LogEntryModel>(
            LogEntryModel(title = "First task", type = LogType.TASK),
            LogEntryModel(title = "Second task", type = LogType.TASK),
            LogEntryModel(title = "Event",type = LogType.EVENT)
        )
        logListAdapter.loadList(list)
    }
}