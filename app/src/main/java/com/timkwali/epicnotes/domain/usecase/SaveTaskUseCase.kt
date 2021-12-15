package com.timkwali.epicnotes.domain.usecase

import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.domain.repository.Repository
import javax.inject.Inject

class SaveTaskUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(task: Task) {
        repository.saveTask(task)
    }
}