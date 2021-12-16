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

        fun bind(task: Task, action: ClickListener<Task>) {
            taskItemBinding.apply {
                val context = itemView.context
                priorityTv.text = task.priority
                categoryTv.text = task.category
                taskCb.text = task.taskName
                timeTv.text = task.time
                taskCb.isChecked = task.isCompleted
                when(task.priority) {
                    context.getString(R.string.high) -> priorityTv.setBackgroundResource(R.drawable.red_bg)
                    context.getString(R.string.medium)  -> priorityTv.setBackgroundResource(R.drawable.orange_bg)
                    context.getString(R.string.low)  -> priorityTv.setBackgroundResource(R.drawable.green_bg)
                }
                when(task.category) {
                    context.getString(R.string.work)  -> {
                        categoryTv.setBackgroundResource(R.drawable.orange_bg)
                        categoryTv.setTextColor(itemView.context.resources.getColor(R.color.text))
                    }
                    context.getString(R.string.school)  -> {
                        categoryTv.setBackgroundResource(R.drawable.pink_bg)
                        categoryTv.setTextColor(itemView.context.resources.getColor(R.color.purple))
                    }
                    context.getString(R.string.family)  -> {
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

    fun getTask(position: Int): Task {
        return tasksList[position]
    }
}