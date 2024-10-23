package com.example.chinazes.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TaskDatabaseHelper(context: Context)
    : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object
    {
        private const val DATABASE_NAME = "task_database.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_TASK = "task"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_SUBTITLE = "subtitle"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE IF NOT EXISTS $TABLE_TASK ( " +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_TITLE TEXT, " +
                "$COLUMN_SUBTITLE TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_TASK")
        onCreate(db)
    }
}