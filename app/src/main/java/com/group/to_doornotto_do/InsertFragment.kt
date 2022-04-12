package com.group.to_doornotto_do

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.group.to_doornotto_do.databinding.FragmentInsertBinding

class InsertFragment : Fragment() {
    private lateinit var binding: FragmentInsertBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertBinding.inflate(layoutInflater)
        return binding.root
    }
}