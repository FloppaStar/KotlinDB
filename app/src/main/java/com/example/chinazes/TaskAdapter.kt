package com.example.chinazes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class TaskAdapter(
    var taskList: MutableList<Task>,
    private val TaskDeleteListener: (Int) -> Unit,
    private val TaskEditListener: (Task, Int) -> Unit)
    : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvSubtitle = itemView.findViewById<TextView>(R.id.tvSubtitle)
        val btEdit = itemView.findViewById<ImageView>(R.id.imEdit)
        val btDelete = itemView.findViewById<ImageView>(R.id.imDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var task = taskList[position]
        holder.tvTitle.text = task.title
        holder.tvSubtitle.text = task.subtitle
        holder.btDelete.setOnClickListener {
            TaskDeleteListener(position)
            taskList.removeAt(position)
            notifyItemRemoved(position)
           update(taskList)
        }
        holder.btEdit.setOnClickListener {
            TaskEditListener(task, position)
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun update(newList: MutableList<Task>) {
        taskList = newList
        notifyDataSetChanged()
    }
}