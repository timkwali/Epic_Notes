package com.timkwali.epicnotes.domain.usecase

data class TaskUseCases(
    val getAllTasksUseCase: GetAllTasksUseCase,
    val saveTaskUseCase: SaveTaskUseCase
)
