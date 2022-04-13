package com.group.to_doornotto_do.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.group.to_doornotto_do.R
import com.group.to_doornotto_do.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding
    private val args: ListFragmentArgs by navArgs()
    private val viewModel: ListFragmentViewModel by viewModels() {
        ListFragmentViewModelFactory(requireContext(), id = args.listId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView: RecyclerView = binding.recyclerviewLists
        val myActivity = (activity as AppCompatActivity)

        myActivity.setSupportActionBar(binding.listTopAppBar)
        myActivity.supportActionBar?.let {
//            it.setHomeAsUpIndicator(R.drawable.ic_baseline_chevron_left_24)
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }

        binding.listTopAppBar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigateUp()
        }

        recyclerView.adapter = ListAdapter()
        binding.listTopAppBar.title = args.listId.toString()

        viewModel.individualList.observe(viewLifecycleOwner, Observer {
            (recyclerView.adapter as ListAdapter).itemsList = it.itemsList.toMutableList()
            (recyclerView.adapter as ListAdapter).notifyDataSetChanged()
        })
    }
}