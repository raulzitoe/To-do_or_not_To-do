package com.group.to_doornotto_do.screens.home

import com.group.to_doornotto_do.model.ToDoListModel
import kotlinx.coroutines.flow.Flow

data class HomeUIState(
    val toDoListsFlow: Flow<List<ToDoListModel>>,
)

