package com.group.to_doornotto_do.screens.todolist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.group.to_doornotto_do.components.ToDoItemCard
import com.group.to_doornotto_do.model.ToDoModel

@Composable
fun ToDoListScreen(
    id: Int,
    modifier: Modifier = Modifier
) {
    val viewmodel: ToDoListViewModel = viewModel(factory = ToDoListViewModelFactory(context = LocalContext.current.applicationContext , id = id))
    val state by viewmodel.state.collectAsStateWithLifecycle()

    ToDoListScreenUI(
        state = state,
        onAddItem = viewmodel::onAddItem,
        onCheckedChange = viewmodel::onCheckedChange,
        modifier = modifier
    )
}

@Composable
private fun ToDoListScreenUI(
    state: ToDoListUIState,
    onAddItem: (toDoList: ToDoModel, value: String) -> Unit,
    onCheckedChange: (toDoList: ToDoModel, itemId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var newItemName by remember { mutableStateOf("") }
    val toDoList by state.toDoItemsFlow.collectAsStateWithLifecycle(initialValue = null)

    toDoList?.let {
        Column(modifier = modifier) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextField(value = newItemName, onValueChange = { newItemName = it })
                Button(
                    onClick = {
                        if (newItemName.isNotBlank()) {
                            onAddItem(it, newItemName)
                            newItemName = ""
                        }
                    }
                ) {
                    Text(text = "Add")
                }
            }

            LazyColumn {
                items(it.itemsList) { toDoItem ->
                    ToDoItemCard(
                        item = toDoItem.itemName,
                        isChecked = toDoItem.isChecked,
                        onCheckedChange = { onCheckedChange(it, toDoItem.itemID) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ToDoListScreenPreview() {

}