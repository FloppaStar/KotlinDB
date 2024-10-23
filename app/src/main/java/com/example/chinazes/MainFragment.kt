package com.example.chinazes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.chinazes.db.TaskDatabaseHelper
import com.example.chinazes.db.TaskRepository
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var taskRepository: TaskRepository
    private lateinit var taskAdapter: TaskAdapter
    private var taskList: MutableList<Task> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskRepository = TaskRepository(TaskDatabaseHelper(view.context))
        recyclerView = view.findViewById(R.id.taskRecyclerView)
        taskList = taskRepository.getAllTasks()
        taskAdapter = TaskAdapter(taskList, { position ->
            taskRepository.deleteTask(taskList[position].id)
            taskList.removeAt(position)
        }, { task, position ->
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerViewMain, TaskEditFragment(task))
                .addToBackStack("fff")
                .commit()
        })

        recyclerView.adapter = taskAdapter

        val btAddTask = view.findViewById<FloatingActionButton>(R.id.fabAddTask)
        btAddTask.setOnClickListener {
            val dialog = BottomSheetDialog(view.context)
            val view = layoutInflater.inflate(R.layout.add_task_bottom_sheet_dialog, null)
            val title = view.findViewById<EditText>(R.id.etAddTitle)
            val subtitle = view.findViewById<EditText>(R.id.etAddSubtitle)
            val btClose = view.findViewById<Button>(R.id.btAddSave)
            btClose.setOnClickListener {
                taskRepository.insertTask(title.text.toString(), subtitle.text.toString())
                taskAdapter.update(taskRepository.getAllTasks())
                taskList = taskRepository.getAllTasks()
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
    }
}