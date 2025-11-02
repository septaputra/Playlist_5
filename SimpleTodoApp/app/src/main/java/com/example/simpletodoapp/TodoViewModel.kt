package com.example.simpletodoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpletodoapp.db.dao.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class TodoViewModel(private val todoDao: TodoDao) : ViewModel() {
    val todoList: LiveData<List<Todo>> = todoDao.getAllTodos()

    fun addTodo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val todo = Todo(title = title, createdAt = Date())
            todoDao.insertTodo(todo)
        }
    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }
}
