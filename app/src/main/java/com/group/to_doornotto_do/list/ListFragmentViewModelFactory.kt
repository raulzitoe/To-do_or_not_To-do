package com.group.to_doornotto_do.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ListFragmentViewModelFactory(private val context: Context, private val id: Int) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListFragmentViewModel(context, id) as T
    }
}