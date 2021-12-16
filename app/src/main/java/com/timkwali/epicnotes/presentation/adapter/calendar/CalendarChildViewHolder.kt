package com.timkwali.epicnotes.presentation.adapter.calendar

import androidx.recyclerview.widget.RecyclerView
import com.timkwali.epicnotes.databinding.CalendarBodyItemBinding
import com.timkwali.epicnotes.domain.model.Task

class CalendarChildViewHolder(private val binding: CalendarBodyItemBinding):
    RecyclerView.ViewHolder(binding.root){

    fun bind(task: Task) {
        binding.apply {
            timeDuration.text = task.time
            taskName.text = task.taskName
            taskDescription.text = task.taskDescription
        }
    }
}