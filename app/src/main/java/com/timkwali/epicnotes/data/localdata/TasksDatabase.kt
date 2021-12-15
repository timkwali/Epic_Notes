package com.timkwali.epicnotes.data.localdata

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timkwali.epicnotes.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1
)
abstract class TasksDatabase: RoomDatabase() {
    abstract val tasksDao: TasksDao

    companion object{
        const val DATABASE_NAME = "tasks_db"
    }
}