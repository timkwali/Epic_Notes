package com.timkwali.epicnotes.presentation.adapter.calendar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.CalendarBodyItemBinding
import com.timkwali.epicnotes.databinding.CalendarHeaderItemBinding
import com.timkwali.epicnotes.databinding.FragmentCalendarBinding
import com.timkwali.epicnotes.domain.model.Task

class CalendarAdapter(
    private val context: Context,
    private val sectionList: List<TaskSection>
): SectionRecyclerViewAdapter<
        TaskSection, Task,
        CalendarSectionViewHolder, CalendarChildViewHolder
>(context, sectionList) {

    override fun onCreateSectionViewHolder(
        sectionViewGroup: ViewGroup?,
        viewType: Int
    ): CalendarSectionViewHolder {
        val binding = DataBindingUtil.inflate<CalendarHeaderItemBinding>(
            LayoutInflater.from(context),
            R.layout.calendar_header_item, sectionViewGroup, false)
        return CalendarSectionViewHolder(binding)
    }

    override fun onCreateChildViewHolder(
        childViewGroup: ViewGroup?,
        viewType: Int
    ): CalendarChildViewHolder {
        val binding = DataBindingUtil.inflate<CalendarBodyItemBinding>(
            LayoutInflater.from(context),
            R.layout.calendar_body_item, childViewGroup, false
        )
        return CalendarChildViewHolder(binding)
    }

    override fun onBindSectionViewHolder(
        p0: CalendarSectionViewHolder?,
        p1: Int,
        p2: TaskSection?
    ) {
        p2?.date?.let { p0?.bind(it) }
    }

    override fun onBindChildViewHolder(p0: CalendarChildViewHolder?, p1: Int, p2: Int, p3: Task?) {
        if (p3 != null) {
            p0?.bind(p3)
        }
    }


}