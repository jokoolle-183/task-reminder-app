package com.example.taskreminderapp.ui.log_entries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.taskreminderapp.R
import com.example.taskreminderapp.data.model.LogEntryModel
import com.example.taskreminderapp.data.model.LogType
import kotlinx.android.synthetic.main.item_log_entry.view.*

class LogListAdapter(private val onDeleteClickListener: OnDeleteClickListener) :
    RecyclerView.Adapter<LogListAdapter.LogViewHolder>() {

    private var itemList: MutableList<LogEntryModel> = mutableListOf()

    fun loadList(items: List<LogEntryModel>) {
        itemList.clear()
        itemList.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_log_entry, parent, false).apply {
            return LogViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    inner class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(logEntry: LogEntryModel?) {
            itemView.apply {
                logEntry?.let { log ->
                    when (log.type) {
                        LogType.TASK -> {
                            typeIcon.setImageDrawable(
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_black_dot
                                )
                            )
                        }

                        LogType.EVENT -> {
                            typeIcon.setImageDrawable(
                                ContextCompat.getDrawable(
                                    context,
                                    R.drawable.ic_star_black_
                                )
                            )
                        }
                    }

                    logTitle.text = log.title

                    deleteIcon.setOnClickListener {
                        onDeleteClickListener.onDeleteClick(logEntry)
                    }
                }
            }
        }
    }

    interface OnDeleteClickListener {
        fun onDeleteClick(logEntry: LogEntryModel)
    }
}