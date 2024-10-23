package com.example.chinazes.db

import android.content.ContentValues
import com.example.chinazes.Task

class TaskRepository(private val dbHelper: TaskDatabaseHelper) {
    fun insertTask(title: String, subtitle: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TaskDatabaseHelper.COLUMN_TITLE, title)
            put(TaskDatabaseHelper.COLUMN_SUBTITLE, subtitle)
        }
        db.insert(TaskDatabaseHelper.TABLE_TASK, null, values)
        db.close()
    }
    fun getAllTasks(): MutableList<Task> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM ${TaskDatabaseHelper.TABLE_TASK}", null)
        val tasks = mutableListOf<Task>()

        with(cursor){
            while(moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TITLE))
                val subtitle = getString(getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_SUBTITLE))
                tasks.add(Task(id, title, subtitle))
            }
        }
        cursor.close()
        db.close()
        return tasks
    }
    fun editTask(task: Task){
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TaskDatabaseHelper.COLUMN_TITLE, task.title)
            put(TaskDatabaseHelper.COLUMN_SUBTITLE, task.subtitle)
        }
        db.update(TaskDatabaseHelper.TABLE_TASK, values, "${TaskDatabaseHelper.COLUMN_ID} = ${task.id}", null)
        db.close()
    }
    fun deleteTask(taskId: Long) {
        val db = dbHelper.writableDatabase
        db.delete(TaskDatabaseHelper.TABLE_TASK,
            "${TaskDatabaseHelper.COLUMN_ID} = $taskId", null)
        db.close()
    }
}