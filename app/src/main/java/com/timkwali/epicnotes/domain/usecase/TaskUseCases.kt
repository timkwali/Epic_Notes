package com.timkwali.epicnotes.domain.usecase

data class TaskUseCases(
    val getAllTasksUseCase: GetAllTasksUseCase,
    val saveTaskUseCase: SaveTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
    val getOldestTasksUseCase: GetOldestTasksUseCase
)
