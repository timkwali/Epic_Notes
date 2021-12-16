package com.timkwali.epicnotes.presentation.adapter

import com.timkwali.epicnotes.domain.model.Task
import com.intrusoft.sectionedrecyclerview.Section

data class TaskSection(
    val tasksList: List<Task>,
    val date: String
): Section<Task> {
    override fun getChildItems(): MutableList<Task> {
        return tasksList as MutableList<Task>
    }
}
