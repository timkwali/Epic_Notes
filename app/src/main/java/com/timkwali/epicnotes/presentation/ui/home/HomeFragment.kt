package com.timkwali.epicnotes.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.FragmentHomeBinding
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.presentation.adapter.TasksRvAdapter
import com.timkwali.epicnotes.presentation.utils.ClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ClickListener<Task> {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var tasksRvAdapter: TasksRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasksRvAdapter = TasksRvAdapter(getList(), this)
        binding.apply {
            notesRv.layoutManager = LinearLayoutManager(requireContext())
            notesRv.setHasFixedSize(true)
            notesRv.adapter = tasksRvAdapter
        }
    }

    private fun getList(): List<Task> {
        return listOf(
            Task(1, "Work", "High", "Create Wireframes", "10:00PM", "24/02/2031", false),
            Task(1, "Family", "Medium", "Create Wireframes", "10:00PM", "24/02/2031", false),
            Task(1, "School", "Low", "Create Wireframes", "10:00PM", "24/02/2031", false),
            Task(1, "Work", "High", "Create Wireframes", "10:00PM", "24/02/2031", false),
            Task(1, "Work", "High", "Create Wireframes", "10:00PM", "24/02/2031", false),
            Task(1, "Work", "High", "Create Wireframes", "10:00PM", "24/02/2031", false),
            Task(1, "Work", "High", "Create Wireframes", "10:00PM", "24/02/2031", false),
            Task(1, "Work", "High", "Create Wireframes", "10:00PM", "24/02/2031", false),
        )
    }

    override fun onItemClick(item: Task, position: Int) {
        Toast.makeText(requireContext(), item.taskName, Toast.LENGTH_SHORT).show()
    }
}