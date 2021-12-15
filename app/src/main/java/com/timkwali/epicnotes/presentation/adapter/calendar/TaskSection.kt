package com.timkwali.epicnotes.presentation.adapter.calendar

import com.timkwali.epicnotes.domain.model.Task
//import com.intrusoft.sectionedrecyclerview.Section

data class TaskSection(
    val tasksList: List<Task>,
    val date: String
)
