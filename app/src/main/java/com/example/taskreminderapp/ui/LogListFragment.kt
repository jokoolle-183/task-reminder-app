package com.example.taskreminderapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskreminderapp.R
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.model.LogType
import com.example.taskreminderapp.ui.adapter.LogListAdapter
import kotlinx.android.synthetic.main.fragment_log_list.view.*

class LogListFragment : Fragment() {

    private lateinit var rvLogList: RecyclerView
    private lateinit var logListAdapter: LogListAdapter

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

    private fun populateAdapter() {
        val list = arrayListOf(
            LogEntryModel(title = "Cestitaj Mikici rodjendan", type = LogType.TASK),
            LogEntryModel(title = "Reci da je volis", type = LogType.TASK),
            LogEntryModel(title = "Ljubaaav", type = LogType.EVENT)
        )
        logListAdapter.loadList(list)
    }
}