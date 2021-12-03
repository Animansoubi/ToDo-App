package com.anahid.todo.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.anahid.todo.R
import com.anahid.todo.databinding.FragmentTaskListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var taskListViewModel: TaskListViewModel

    @Inject
    lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTask.adapter = taskAdapter
        binding.rvTask.layoutManager = LinearLayoutManager(context)

        taskListViewModel.taskList.observe(viewLifecycleOwner) {
            taskAdapter.setItems(it)
        }

        taskListViewModel.showAddEditFragment.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_TaskListFragment_to_AddEditFragment)
        }

        binding.fabAddTask.setOnClickListener {
            taskListViewModel.onAddButtonClicked()
        }

        taskListViewModel.showProgress.observe(viewLifecycleOwner) {
            binding.prg.visibility = if (it) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }

        taskListViewModel.showNoDataView.observe(viewLifecycleOwner) {
            binding.tvNoData.visibility = if (it) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }

        taskListViewModel.searchedTaskList.observe(viewLifecycleOwner) {

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}