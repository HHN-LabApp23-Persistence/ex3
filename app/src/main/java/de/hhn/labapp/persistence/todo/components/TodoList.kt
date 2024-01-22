package de.hhn.labapp.persistence.todo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.hhn.labapp.persistence.todo.viewmodel.TodoListViewModel

@Composable
fun TodoList() {
    val viewModel = TodoListViewModel(LocalContext.current)

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = viewModel::onAddItem,
            ) {
                Icon(Icons.Filled.Add, "Floating Action Button")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            Heading()
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp,)
                    .padding(bottom = 16.dp),
                onQueryChanged = viewModel::search,
            )
            TodoItemsList(viewModel)
        }

        if (viewModel.showDialog) {
            TodoItemDialog(viewModel)
        }
    }
}

@Composable
private fun Heading() {
    Text(
        modifier = Modifier.padding(
            horizontal = 4.dp,
            vertical = 16.dp,
        ),
        fontSize = MaterialTheme.typography.headlineMedium.fontSize,
        fontWeight = FontWeight.Bold,
        text = "Tasks",
    )
}

@Composable
private fun TodoItemsList(viewModel: TodoListViewModel) {
    LazyColumn {
        items(
            viewModel.todoItems.sortedBy { it.completed }
        ) { todoItem ->
            TodoItemCard(
                item = todoItem,
                onItemChecked = { isChecked, item ->
                    viewModel.onItemChecked(isChecked, item)
                },
                onClick = viewModel::onItemClicked,
            )
        }
    }
}