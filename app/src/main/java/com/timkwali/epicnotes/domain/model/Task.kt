package com.timkwali.epicnotes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val category: String,
    val priority: String,
    val taskName: String,
    val taskDescription: String,
    val time: String,
    val date: String,
    val isReminderOn: Boolean,
    var isCompleted: Boolean
)
