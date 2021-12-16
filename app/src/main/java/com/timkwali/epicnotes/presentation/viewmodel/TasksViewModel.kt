package com.timkwali.epicnotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.domain.usecase.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
): ViewModel() {

    val allTasks = taskUseCases.getAllTasksUseCase.invoke().asLiveData()

    fun saveTask(task: Task) = viewModelScope.launch {
        taskUseCases.saveTaskUseCase(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch {
        taskUseCases.updateTaskUseCase(task)
    }
}