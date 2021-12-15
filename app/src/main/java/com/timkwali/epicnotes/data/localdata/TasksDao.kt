package com.timkwali.epicnotes.data.localdata

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.timkwali.epicnotes.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: Task)

    @Query("SELECT * FROM task")
    fun getAllTasks(): Flow<List<Task>>
}