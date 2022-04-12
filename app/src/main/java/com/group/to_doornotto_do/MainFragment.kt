package com.group.to_doornotto_do

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.recyclerviewLists
        recyclerView.adapter = ToDoAdapter()
        viewModel.getListData().observe(viewLifecycleOwner) {
            (recyclerView.adapter as ToDoAdapter).toDoList = it
            (recyclerView.adapter as ToDoAdapter).notifyDataSetChanged()
        }
        viewModel.insert(ToDoModel(listName = "test1", itemsList = listOf()))
    }

}