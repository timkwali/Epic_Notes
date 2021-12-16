package com.timkwali.epicnotes.domain.usecase

import android.util.Log
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.domain.repository.Repository
import com.timkwali.epicnotes.presentation.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetOldestTasksUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke() = flow {
        val finalList = mutableListOf<Task>()
        var dates = mutableListOf<Long>()
        repository.getAllTasks().collect { allTasks ->
            for(task in allTasks) {
                if(!task.isCompleted) {
                    dates.add(Utils.dateToTimeStamp(task.date))
                }
            }
            dates = dates.distinct().toMutableList()
            dates.sort()
            for(task in allTasks) {
                if(Utils.dateToTimeStamp(task.date) == dates[0]) {
                    finalList.add(task)
                }
            }
            emit(finalList.toList())
        }
    }
}