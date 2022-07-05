package com.group.to_doornotto_do.list

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.R
import com.group.to_doornotto_do.databinding.FragmentListBinding
import com.group.to_doornotto_do.repository.ToDoItemListModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val args: ListFragmentArgs by navArgs()
    private val viewModel: ListFragmentViewModel by viewModels() {
        ListFragmentViewModelFactory(requireActivity().application, id = args.listId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar_list, menu)
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

        myActivity.setSupportActionBar(binding.listTopAppBar)
        myActivity.supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.listTopAppBar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        recyclerView.adapter = ItemListAdapter(listener = object: ItemListAdapter.ListAdapterListener{
            override fun itemChecked() {
                viewModel.updateList()
            }

            override fun deleteItem(item: ToDoItemListModel) {
                viewModel.deleteItem(item)
            }
        })

        binding.listTopAppBar.title = args.listId.toString()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.individualList.collect {
                        (recyclerView.adapter as ItemListAdapter).submitList(it.itemsList)
                        binding.listTopAppBar.title = it.listName
                    }
                }
                launch {
                    viewModel.deleteState.collect {
                        (recyclerView.adapter as ItemListAdapter).deleteState = it
                        (recyclerView.adapter as ItemListAdapter).notifyDataSetChanged()
                    }
                }
            }
        }

        binding.editTextNewItem.setOnEditorActionListener { _, actionId, _ ->
            val itemName = binding.editTextNewItem.text

            if(actionId == EditorInfo.IME_ACTION_DONE){
                binding.editTextNewItem.setText("")
                viewModel.insertItem(itemName.toString())
                binding.editTextNewItem.clearFocus()
                binding.createNewItemLayout.isVisible = false
                binding.fab.isActivated = false
                val rotateView: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_anticlockwise)
                binding.fab.startAnimation(rotateView)
                false
            } else {
                false
            }
        }

        binding.fab.setOnClickListener {
            binding.createNewItemLayout.isVisible = !binding.createNewItemLayout.isVisible
            binding.fab.isActivated = !binding.fab.isActivated
            val imm = binding.editTextNewItem.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            if(binding.fab.isActivated) {
                binding.editTextNewItem.requestFocus()
                imm.showSoftInput(binding.editTextNewItem, 0)


                val rotateView: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_clockwise)
                binding.fab.startAnimation(rotateView)

                val scaleView: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.drop_down)
                binding.createNewItemLayout.startAnimation(scaleView)
            }
            else{
                imm.hideSoftInputFromWindow(binding.createNewItemLayout.windowToken, 0)
                val rotateView: Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate_anticlockwise)
                binding.fab.startAnimation(rotateView)
            }
        }
    }
}