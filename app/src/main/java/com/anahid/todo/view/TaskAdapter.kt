package com.anahid.todo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anahid.todo.databinding.ListItemTaskBinding
import com.anahid.todo.domain.model.ModelToDoItem

class TaskAdapter(private val context: Context) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private val li = LayoutInflater.from(context)
    private val items: MutableList<ModelToDoItem> = mutableListOf()

    fun setItems(items: List<ModelToDoItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemTaskBinding.inflate(li))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: ListItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(modelToDoItem: ModelToDoItem) {
            binding.txtTitle.text = modelToDoItem.title
            binding.txtDescription.text = modelToDoItem.description
            binding.checkboxIsDone.isChecked = modelToDoItem.isDone
            binding.root.setOnClickListener {
            }
        }
    }
}