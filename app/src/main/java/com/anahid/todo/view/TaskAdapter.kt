package com.anahid.todo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anahid.todo.databinding.ListItemTaskBinding
import com.anahid.todo.domain.model.ToDoTaskModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TaskAdapter @Inject constructor(@ApplicationContext private val context: Context) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val li = LayoutInflater.from(context)
    private val taskModels: MutableList<ToDoTaskModel> = mutableListOf()

    fun setItems(taskModels: List<ToDoTaskModel>) {
        this.taskModels.clear()
        this.taskModels.addAll(taskModels)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTaskBinding.inflate(li))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(taskModels[position])
    }

    override fun getItemCount(): Int = taskModels.size

    inner class ViewHolder(private val binding: ListItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(toDoTaskModel: ToDoTaskModel) {
            binding.txtTitle.text = toDoTaskModel.title
            binding.txtDescription.text = toDoTaskModel.description
            binding.checkboxIsDone.isChecked = toDoTaskModel.isDone
            binding.root.setOnClickListener {
            }
        }
    }
}