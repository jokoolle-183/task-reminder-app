package com.example.taskreminderapp.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.taskreminderapp.LogViewModel
import com.example.taskreminderapp.R
import kotlinx.android.synthetic.main.fragment_log_detail.*
import javax.inject.Inject

class LogDetailFragment : Fragment() {

    @Inject
    protected lateinit var logViewModel: LogViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

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

        fabSave.setOnClickListener {

        }
    }
}