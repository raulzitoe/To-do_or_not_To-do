package com.group.to_doornotto_do.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.group.to_doornotto_do.components.ToDoListCard
import com.group.to_doornotto_do.model.ToDoModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    onOpenList: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewmodel: HomeViewModel = viewModel()
    val state by viewmodel.state.collectAsStateWithLifecycle()

    HomeScreenUI(
        state = state,
        onCreateNewList = viewmodel::onCreateNewList,
        onOpenList = onOpenList,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun HomeScreenUI(
    state: HomeUIState,
    onCreateNewList: (name: String) -> Unit,
    onOpenList: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val toDoLists by state.toDoListsFlow.collectAsStateWithLifecycle(initialValue = listOf())
    var newListName by remember { mutableStateOf("") }

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(value = newListName, onValueChange = { newListName = it })
            Button(
                onClick = {
                    if (newListName.isNotBlank()) {
                        onCreateNewList(newListName)
                        newListName = ""
                    }
                }
            ) {
                Text(text = "Create")
            }
        }

        LazyColumn(modifier = modifier) {
            items(toDoLists) { toDoList ->
                ToDoListCard(
                    listName = toDoList.listName,
                    quantityItems = toDoList.itemsList.size,
                    modifier = Modifier.clickable { onOpenList(toDoList.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreenUI(
        state = HomeUIState(
            toDoListsFlow = flowOf(listOf(ToDoModel(1, "Shopping List", listOf())))
        ),
        onCreateNewList = {},
        onOpenList = {}
    )
}
