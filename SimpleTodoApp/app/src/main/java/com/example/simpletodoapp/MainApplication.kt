package com.example.simpletodoapp

import android.app.Application
import com.example.simpletodoapp.db.TodoDatabase
import com.example.simpletodoapp.db.dao.TodoDao

class MainApplication : Application() {
    lateinit var database: TodoDatabase
    lateinit var todoDao: TodoDao

    override fun onCreate() {
        super.onCreate()
        database = TodoDatabase.getInstance(this)
        todoDao = database.todoDao()
    }
}
