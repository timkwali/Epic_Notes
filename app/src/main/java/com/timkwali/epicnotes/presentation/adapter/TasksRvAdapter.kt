package com.timkwali.epicnotes.presentation.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.timkwali.epicnotes.R
import com.timkwali.epicnotes.databinding.NotesItemBinding
import com.timkwali.epicnotes.domain.model.Task
import com.timkwali.epicnotes.presentation.utils.ClickListener

class TasksRvAdapter(
    private val tasksList: List<Task>,
    private val clickListener: ClickListener<Task>
): RecyclerView.Adapter<TasksRvAdapter.TasksViewHolder>() {

    inner class TasksViewHolder(var taskItemBinding: NotesItemBinding):
        RecyclerView.ViewHolder(taskItemBinding.root) {
        fun setClick(task: Task, clickListener: ClickListener<Task>) {

        }

        fun bind(task: Task, action: ClickListener<Task>) {
            taskItemBinding.apply {
                priorityTv.text = task.priority
                categoryTv.text = task.category
                taskCb.text = task.taskName
                timeTv.text = task.time
                when(task.priority) {
                    "High" -> priorityTv.setBackgroundResource(R.drawable.red_bg)
                    "Medium" -> priorityTv.setBackgroundResource(R.drawable.orange_bg)
                    "Low" -> priorityTv.setBackgroundResource(R.drawable.green_bg)
                }
                when(task.category) {
                    "Work" -> {
                        categoryTv.setBackgroundResource(R.drawable.orange_bg)
                        categoryTv.setTextColor(itemView.context.resources.getColor(R.color.text))
                    }
                    "School" -> {
                        categoryTv.setBackgroundResource(R.drawable.pink_bg)
                        categoryTv.setTextColor(itemView.context.resources.getColor(R.color.purple))
                    }
                    "Family" -> {
                        categoryTv.setBackgroundResource(R.drawable.cyan_bg)
                        categoryTv.setTextColor(itemView.context.resources.getColor(R.color.blue))
                    }
                }
            }

            itemView.setOnClickListener {
                clickListener.onItemClick(task, adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        return TasksViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.notes_item,
                parent, false
            )
        )
    }



    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentTask = tasksList[position]
        holder.bind(currentTask, clickListener)
    }

    override fun getItemCount(): Int = tasksList.size
}