package com.timkwali.epicnotes.data.localdata

import androidx.room.*
import com.timkwali.epicnotes.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: Task)

    @Query("SELECT * FROM tasks_table")
    fun getAllTasks(): Flow<List<Task>>

    @Update
    suspend fun updateTask(task: Task)
}