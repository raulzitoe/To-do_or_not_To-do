package com.group.to_doornotto_do.home

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.R
import com.group.to_doornotto_do.ToDoAdapter
import com.group.to_doornotto_do.ToDoModel
import com.group.to_doornotto_do.databinding.FragmentHomeBinding
import com.group.to_doornotto_do.list.ListAdapter

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete ->
                viewModel.toggleDelete()
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.recyclerviewLists
        val myActivity = (activity as AppCompatActivity)

        myActivity.setSupportActionBar(binding.homeTopAppBar)

        with(recyclerView) {
            adapter = ToDoAdapter(listener = object : ToDoAdapter.ToDoItemListener {
                override fun itemClick(id: Int) {
                    val action = HomeFragmentDirections.actionMainFragmentToListFragment(id)
                    Navigation.findNavController(view).navigate(action)
                }

                override fun deleteList(list: ToDoModel) {
                    viewModel.deleteList(list)
                }
            })
        }

        viewModel.getListData().observe(viewLifecycleOwner) {
            (recyclerView.adapter as ToDoAdapter).toDoList = it
            (recyclerView.adapter as ToDoAdapter).notifyDataSetChanged()
        }

        viewModel.deleteState.observe(viewLifecycleOwner) {
            (recyclerView.adapter as ToDoAdapter).deleteState = it
            (recyclerView.adapter as ToDoAdapter).notifyDataSetChanged()
        }

        binding.editTextNewList.setOnEditorActionListener { _, actionId, _ ->
            val text = binding.editTextNewList.text
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.editTextNewList.setText("")
                viewModel.insert(ToDoModel(listName = text.toString(), itemsList = listOf()))
                binding.editTextNewList.clearFocus()
                binding.createNewListLayout.isVisible = false
                binding.fab.isActivated = false
                val rotateView: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_anticlockwise)
                binding.fab.startAnimation(rotateView)

                false
            } else {
                false
            }
        }

        binding.fab.setOnClickListener {
            binding.createNewListLayout.isVisible = !binding.createNewListLayout.isVisible
            binding.fab.isActivated = !binding.fab.isActivated
            val imm =
                binding.editTextNewList.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (binding.fab.isActivated) {
                binding.editTextNewList.requestFocus()
                imm.showSoftInput(binding.editTextNewList, 0)

                val rotateView: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_clockwise)
                binding.fab.startAnimation(rotateView)

                val scaleView: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.drop_down)
                binding.createNewListLayout.startAnimation(scaleView)
            } else {
                imm.hideSoftInputFromWindow(binding.editTextNewList.windowToken, 0)
                val rotateView: Animation =
                    AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_anticlockwise)
                binding.fab.startAnimation(rotateView)
            }
        }
    }

}