package com.timkwali.epicnotes.presentation.ui.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.FragmentCalendarBinding
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.presentation.adapter.calendar.CalendarAdapter
import com.timkwali.epicnotes.presentation.adapter.calendar.TaskSection
import com.timkwali.epicnotes.presentation.viewmodel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private val viewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_calendar, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        setupToolbar(toolbar)

        viewModel.allTasks.observe(viewLifecycleOwner, {
            binding.noTasksTv.isVisible = it.isNullOrEmpty()
            if(it.isNotEmpty()) {
                setUpTasks(it)
            }
        })
    }

    private fun setupToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar: ActionBar? = (activity as AppCompatActivity).supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_round_keyboard_backspace_24)
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        }
        actionBar?.title = ""
    }

    private fun setUpTasks(tasksList: List<Task>) {
        binding.apply {
            /** SET UP TRANSACTIONS BY DATE */
            val sections = mutableListOf<TaskSection>()
            val dates = mutableListOf<String>()
            for (task in tasksList) {
                dates.add(task.date)
            }
            for (date in dates.distinct()) {
                val childList = mutableListOf<Task>()
                for (task in tasksList) {
                    if (date == task.date) {
                        childList.add(task)
                    }
                }
                sections.add(TaskSection(childList, setUpDate(date)))
            }
            calendarRv.layoutManager = LinearLayoutManager(requireContext())
            calendarRv.setHasFixedSize(true)
            val adapter = CalendarAdapter(
                requireContext(),
                sections as List<TaskSection>
            )
            calendarRv.adapter = adapter
        }
    }

    private fun setUpDate(date: String): String {
        val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT).parse(date);
        val day = date.substring(0, 2)
        var dayOfWeek = when (sdf.day) {
            0 -> "Sunday"
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            else -> ""
        }
        val month = when (date.substring(3, 5)) {
            "01" -> "January"
            "02" -> "February"
            "03" -> "March"
            "04" -> "April"
            "05" -> "May"
            "06" -> "June"
            "07" -> "July"
            "08" -> "August"
            "09" -> "September"
            "10" -> "October"
            "11" -> "November"
            "12" -> "December"
            else -> ""
        }
        val today = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        dayOfWeek = if(today == day.toInt()) "Today" else dayOfWeek
        return "$dayOfWeek $day, $month"
    }
}