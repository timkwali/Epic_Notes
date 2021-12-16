package com.timkwali.epicnotes.data.repository

import com.timkwali.epicnotes.data.localdata.TasksDatabase
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val taskDb: TasksDatabase
): Repository {
    private val taskDao = taskDb.tasksDao

    override suspend fun saveTask(task: Task) {
        taskDao.saveTask(task)
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
}