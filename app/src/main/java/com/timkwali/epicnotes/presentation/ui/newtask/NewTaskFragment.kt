package com.timkwali.epicnotes.presentation.ui.newtask

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.FragmentNewTaskBinding
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.presentation.utils.Utils
import com.timkwali.epicnotes.presentation.viewmodel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding
    private val viewModel: TasksViewModel by activityViewModels()
    private val category: String = ""
    private val priority: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_task, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lowBtn.setOnClickListener {
        }
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        setupToolbar(toolbar)
        setHasOptionsMenu(true)

        setUpDate()
//        setUpAlarm()
//        setUpReminder()
//        setUpPriority()
//        setUpCategory()
    }

    private fun setUpDate() {
        binding.dateTv.setOnClickListener {
            Utils.showDatePicker(
                parentFragmentManager,
                getString(R.string.date)
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.save) {
            saveTask()
            true
        } else {
            super.onOptionsItemSelected(item)
            false
        }
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

    private fun saveTask() {
        if(validateForm()) {
            binding.apply {
                viewModel.saveTask(
                    Task(
                        category = category,
                        priority = priority,
                        taskName = taskNameEt.text.toString(),
                        taskDescription = taskDescriptionEt.text.toString(),
                        time = alarmTv.text.toString(),
                        date = dateTv.text.toString(),
                        isReminderOn = remindSw.isChecked,
                        isCompleted = false
                    )
                )
            }
            findNavController().popBackStack()
            Toast.makeText(requireContext(), "task saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateForm(): Boolean {
        if()
    }
}