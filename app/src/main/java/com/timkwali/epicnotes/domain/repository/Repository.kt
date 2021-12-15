package com.timkwali.epicnotes.domain.repository

import com.timkwali.epicnotes.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun saveTask(task: Task)

    suspend fun getAllTasks(): Flow<List<Task>>
}