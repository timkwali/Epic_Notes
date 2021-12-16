package com.timkwali.epicnotes.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.FragmentHomeBinding
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.presentation.utils.SwipeCallback
import com.timkwali.epicnotes.presentation.adapter.TasksRvAdapter
import com.timkwali.epicnotes.presentation.utils.ClickListener
import com.timkwali.epicnotes.presentation.utils.Utils.setUpDate
import com.timkwali.epicnotes.presentation.utils.Utils.showSnackBar
import com.timkwali.epicnotes.presentation.viewmodel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment(), ClickListener<Task> {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var tasksRvAdapter: TasksRvAdapter
    private val viewModel: TasksViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.oldestTasks.observe(viewLifecycleOwner, {
                noTasksTv.isVisible = it.isNullOrEmpty()
                date.isVisible = !it.isNullOrEmpty()
                if(!it.isNullOrEmpty()) {
                    val tasksDate = setUpDate(it[0].date)
                    date.text = tasksDate

                    tasksRvAdapter = TasksRvAdapter(it, this@HomeFragment)
                    notesRv.layoutManager = LinearLayoutManager(requireContext())
                    notesRv.setHasFixedSize(true)
                    notesRv.adapter = tasksRvAdapter
                    updateTask(ItemTouchHelper.RIGHT)
                    updateTask(ItemTouchHelper.LEFT)
                }
            })
        }
    }

    override fun onItemClick(item: Task, position: Int) {
        showSnackBar(item.taskName)
    }

    private fun updateTask(swipeDirection: Int) {
        val swipeGesture = object : SwipeCallback(swipeDirection) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                super.onSwiped(viewHolder, direction)
                val task = tasksRvAdapter.getTask(viewHolder.adapterPosition)
                task.isCompleted = swipeDirection == ItemTouchHelper.RIGHT
                viewModel.updateTask(task)
                val message = if(swipeDirection == ItemTouchHelper.RIGHT) "Task completed!" else "Task not completed!"
                showSnackBar(message)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeGesture)
        itemTouchHelper.attachToRecyclerView(binding.notesRv)
    }
}