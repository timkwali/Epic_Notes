package com.timkwali.epicnotes.presentation.adapter.calendar

import androidx.recyclerview.widget.RecyclerView
import com.timkwali.epicnotes.databinding.CalendarHeaderItemBinding

class CalendarSectionViewHolder(private val binding: CalendarHeaderItemBinding):
    RecyclerView.ViewHolder(binding.root){
        fun bind(date: String) {
            binding.date.text = date
        }
}