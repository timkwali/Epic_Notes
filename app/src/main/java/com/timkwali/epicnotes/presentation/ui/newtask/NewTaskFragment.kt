package com.timkwali.epicnotes.presentation.ui.newtask

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.FragmentNewTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewTaskFragment : Fragment() {
   private lateinit var binding: FragmentNewTaskBinding

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
            Toast.makeText(requireContext(), "lkfnasjkff", Toast.LENGTH_SHORT).show()
        }
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        setupToolbar(toolbar)
        setHasOptionsMenu(true)

        setUpDate()
        setUpAlarm()
        setUpReminder()
        setUpPriority()
        setUpCategory()
    }

    private fun setUpDate() {
        binding.dateTv.setOnClickListener {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if(item.itemId == R.id.save) {
            Toast.makeText(requireContext(), "task saved", Toast.LENGTH_SHORT).show()
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
}