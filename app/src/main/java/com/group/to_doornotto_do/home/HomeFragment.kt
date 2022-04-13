package com.group.to_doornotto_do.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.ToDoAdapter
import com.group.to_doornotto_do.ToDoItemListModel
import com.group.to_doornotto_do.ToDoModel
import com.group.to_doornotto_do.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.recyclerviewLists
        with(recyclerView) {
            adapter = ToDoAdapter(listener = object: ToDoAdapter.ToDoItemListener{
                override fun itemClick(id: Int) {
                    val action = HomeFragmentDirections.actionMainFragmentToListFragment(id)
                    Navigation.findNavController(view).navigate(action)
                }
            })
        }
        viewModel.getListData().observe(viewLifecycleOwner) {
            (recyclerView.adapter as ToDoAdapter).toDoList = it
            (recyclerView.adapter as ToDoAdapter).notifyDataSetChanged()
        }
        viewModel.insert(ToDoModel(listName = "teste", itemsList = listOf(ToDoItemListModel("cafe", true), ToDoItemListModel("arroz", false))))

        binding.editTextNewList.setOnEditorActionListener { view, actionId, keyEvent ->
            val text = binding.editTextNewList.text
            if(actionId == EditorInfo.IME_ACTION_DONE){
                Toast.makeText(context, "deu isso: $text ", Toast.LENGTH_LONG).show()
                binding.editTextNewList.setText("")
                viewModel.insert(ToDoModel(listName = text.toString(), itemsList = listOf()))
                binding.editTextNewList.clearFocus()
                false
            } else {
                false
            }
        }

        binding.fab.setOnClickListener {
            viewModel.deleteAll()
        }
    }

}