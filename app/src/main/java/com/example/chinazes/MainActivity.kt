package com.example.chinazes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.chinazes.db.TaskDatabaseHelper
import com.example.chinazes.db.TaskRepository
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var taskRepository: TaskRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        taskRepository = TaskRepository(TaskDatabaseHelper(this))
//
//        taskRepository.insertTask("Заголовок ДАНЕТ ПАКЕвыфвфывфывфыТ", "Подзаголовок НЕТфывфывфывфПАКЕТ ДА")
//
//        val tasks = taskRepository.getAllTasks()
//        tasks.forEach{
//            Log.d("LLL", "Task ID: ${it.id}, Title: ${it.title}, Subtitle: ${it.subtitle}")
//        }

//        if (tasks.isNotEmpty()) {
//            taskRepository.deleteTask(tasks[0].id)
//        }
    }
}