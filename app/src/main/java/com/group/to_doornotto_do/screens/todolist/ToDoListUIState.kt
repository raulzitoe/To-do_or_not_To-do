package com.group.to_doornotto_do.screens.todolist

import com.group.to_doornotto_do.model.ToDoListModel
import kotlinx.coroutines.flow.Flow

data class ToDoListUIState(
    val toDoItemsFlow: Flow<ToDoListModel?>
)