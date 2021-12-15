package com.timkwali.epicnotes.domain.usecase

import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Task>> {
        return repository.getAllTasks()
    }
}