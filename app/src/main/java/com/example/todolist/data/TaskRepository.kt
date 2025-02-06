package com.example.todolist.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskRepository {
    private val _tasks= MutableStateFlow<List<Task>>(emptyList())
    val tasks =_tasks.asStateFlow()

    fun addTask(name: String){
        val task=Task(
            id = _tasks.value.size + 1,
            name = name,
            isDone = false
        )
        _tasks.value = _tasks.value + task

    }

    fun  updateTask(task: Task){
        _tasks.value = _tasks.value.map{ taskItem ->
            if (taskItem.id == task.id){
                task.copy(isDone = !task.isDone)
            }
            else{
                taskItem
            }
        }
    }
}