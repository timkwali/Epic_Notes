package com.timkwali.epicnotes.presentation.ui.newtask

import android.os.Bundle
import android.view.*
import android.widget.Button
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
import com.timkwali.epicnotes.presentation.utils.Utils.showSnackBar
import com.timkwali.epicnotes.presentation.viewmodel.TasksViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : Fragment() {
    private lateinit var binding: FragmentNewTaskBinding
    private val viewModel: TasksViewModel by activityViewModels()
    private var category: String = ""
    private var priority: String = ""

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

        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        setupToolbar(toolbar)
        setHasOptionsMenu(true)
        binding.apply {
            lowBtn.setOnClickListener { setUpPriority(lowBtn) }
            mediumBtn.setOnClickListener { setUpPriority(mediumBtn) }
            highBtn.setOnClickListener { setUpPriority(highBtn) }
            workBtn.setOnClickListener { setUpCategory(workBtn) }
            schoolBtn.setOnClickListener { setUpCategory(schoolBtn) }
            familyBtn.setOnClickListener { setUpCategory(familyBtn) }
        }
        setUpDate()
        setUpAlarm()
    }

    private fun setUpDate() {
        binding.dateTv.setOnClickListener {
            Utils.showDatePicker(
                parentFragmentManager,
                getString(R.string.date)
            )
        }
    }

    private fun setUpAlarm() {}

    private fun resetPriorityButtons() {
        binding.apply {
            lowBtn.setBackgroundResource(R.drawable.green_stroke_bg)
            lowBtn.setTextColor(resources.getColor(R.color.text))
            mediumBtn.setBackgroundResource(R.drawable.orange_stroke_bg)
            mediumBtn.setTextColor(resources.getColor(R.color.text))
            highBtn.setBackgroundResource(R.drawable.red_stroke_bg)
            highBtn.setTextColor(resources.getColor(R.color.text))
        }
    }

    private fun setUpPriority(button: Button) {
        resetPriorityButtons()
        priority = button.text.toString()
        button.setBackgroundResource(R.drawable.grey_bg)
        button.setTextColor(resources.getColor(R.color.white))
    }

    private fun resetCategoryButtons() {
        binding.apply {
            workBtn.setBackgroundResource(R.drawable.orange_bg)
            workBtn.setTextColor(resources.getColor(R.color.text))
            familyBtn.setBackgroundResource(R.drawable.cyan_bg)
            familyBtn.setTextColor(resources.getColor(R.color.blue))
            schoolBtn.setBackgroundResource(R.drawable.pink_bg)
            schoolBtn.setTextColor(resources.getColor(R.color.purple))
        }
    }

    private fun setUpCategory(button: Button) {
        resetCategoryButtons()
        category = button.text.toString()
        button.setBackgroundResource(R.drawable.grey_bg)
        button.setTextColor(resources.getColor(R.color.white))
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
            showSnackBar("Task saved!")
        } else {
            showSnackBar("Please complete the form to continue!")
        }
    }

    private fun validateForm(): Boolean {
        binding.apply {
            return  category.isNotEmpty() && priority.isNotEmpty() &&
                    taskNameEt.text.toString().trim().isNotEmpty() &&
                    taskDescriptionEt.text.toString().trim().isNotEmpty() &&
                    alarmTv.text.toString() != getString(R.string.alarm) &&
                    dateTv.text.toString() != getString(R.string.date)
        }
    }
}