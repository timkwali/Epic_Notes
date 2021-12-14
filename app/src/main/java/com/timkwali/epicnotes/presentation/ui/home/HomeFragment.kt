package com.timkwali.epicnotes.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.FragmentHomeBinding
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.presentation.adapter.TasksRvAdapter
import com.timkwali.epicnotes.presentation.utils.ClickListener

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
            Task("Work", "High", "Create Wireframes", "10:00PM"),
            Task("Family", "Medium", "Create Wireframes", "10:00PM"),
            Task("School", "Low", "Create Wireframes", "10:00PM"),
            Task("Work", "High", "Create Wireframes", "10:00PM"),
            Task("Work", "High", "Create Wireframes", "10:00PM"),
            Task("Work", "High", "Create Wireframes", "10:00PM"),
            Task("Work", "High", "Create Wireframes", "10:00PM"),
            Task("Work", "High", "Create Wireframes", "10:00PM"),
        )
    }

    override fun onItemClick(item: Task, position: Int) {

    }
}