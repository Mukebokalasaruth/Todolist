package com.example.todolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.mvvmandroid.data.Task
import com.example.todolist.data.Task
import com.example.todolist.data.TaskRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()

    val tasks: StateFlow<List<Task>> = repository.tasks


    fun addTask(name: String) {
        viewModelScope.launch {
            repository.addTask(name)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {

            repository.updateTask(task)
        }
    }
}
