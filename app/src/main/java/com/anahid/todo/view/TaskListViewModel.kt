package com.anahid.todo.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anahid.todo.domain.ToDoRepository
import com.anahid.todo.domain.model.ToDoTaskModel
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class TaskListViewModel @Inject constructor(private val toDoRepo: ToDoRepository) {

    private val internalShowProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = internalShowProgress

    private val internalShowNoDataView = MutableLiveData<Boolean>()
    val showNoDataView: LiveData<Boolean>
        get() = internalShowNoDataView

    private val internalTaskList = MutableLiveData<List<ToDoTaskModel>>()
    val taskList: LiveData<List<ToDoTaskModel>>
        get() = internalTaskList

    private val internalSortedTaskList =
        MutableLiveData<List<ToDoTaskModel>>()
    val sortedTaskList: LiveData<List<ToDoTaskModel>>
        get() = internalSortedTaskList

    private val internalShowAddEditFragment = MutableLiveData<Unit>()
    val showAddEditFragment: LiveData<Unit>
        get() = internalShowAddEditFragment

    private val internalSearchedTaskList =
        MutableLiveData<List<ToDoTaskModel>>()
    val searchedTaskList: LiveData<List<ToDoTaskModel>>
        get() = internalSearchedTaskList

    fun onLoad() {
        internalShowNoDataView.value = true
        internalTaskList.value = toDoRepo.loadAll()
        internalShowNoDataView.value = false
    }

    fun onDeleteAllButtonClicked() {
        internalShowProgress.value = true
        toDoRepo.deleteAll()
        internalShowNoDataView.value = true
    }

    fun onSortButtonClicked(priority: String) {
        when (priority) {
            HIGH -> {
                toDoRepo.getSortedTaskByHighestPriority()
            }
            LOW -> {
                toDoRepo.getSortedTaskByLowestPriority()
            }
        }
    }

    fun onAddButtonClicked() {
        internalShowAddEditFragment.value = Unit
    }

    fun onSearchButtonClicked(searchPhrase: String) {
        internalSearchedTaskList.value = toDoRepo.getTaskBySearchPhrase(searchPhrase)
    }

    companion object {
        private const val HIGH = "high"
        private const val LOW = "low"
    }
}