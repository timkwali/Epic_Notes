package com.timkwali.epicnotes.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id: Int = 1,
    val category: String,
    val priority: String,
    val taskName: String,
    val time: String,
    val date: String,
    val isReminderOn: Boolean
)
