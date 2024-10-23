package com.example.chinazes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.chinazes.db.TaskDatabaseHelper
import com.example.chinazes.db.TaskRepository

class TaskEditFragment(var task: Task) : Fragment() {
    private lateinit var taskRepository: TaskRepository
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = view.findViewById<EditText>(R.id.etTitle)
        val subtitle = view.findViewById<EditText>(R.id.etSubtitle)
        title.setText(task.title)
        subtitle.setText(task.subtitle)
        val btSave = view.findViewById<Button>(R.id.btSave)
        taskRepository = TaskRepository(TaskDatabaseHelper(view.context))
        btSave.setOnClickListener{
            task.title = title.text.toString()
            task.subtitle = subtitle.text.toString()
            taskRepository.editTask(task)
            parentFragmentManager.popBackStack()
        }
    }
}