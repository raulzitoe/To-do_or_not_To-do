package com.group.to_doornotto_do.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.group.to_doornotto_do.ToDoModel
import com.group.to_doornotto_do.ToDoRepository

class ListFragmentViewModel(context: Context, id: Int) : ViewModel() {
    private val repository = ToDoRepository(context)
    var individualList: LiveData<ToDoModel> = repository.getIndividualListData(id)

}