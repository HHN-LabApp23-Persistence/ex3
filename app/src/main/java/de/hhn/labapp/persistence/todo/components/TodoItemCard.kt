package de.hhn.labapp.persistence.todo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.hhn.labapp.persistence.todo.model.TodoItem

@Composable
fun TodoItemCard(
    item: TodoItem,
    onItemChecked: (Boolean, TodoItem) -> Unit,
    onClick: (TodoItem) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(),
        onClick = { onClick(item) }
    ) {
        Row(
            modifier = Modifier.padding(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.primary,
                ),
                checked = item.completed,
                onCheckedChange = { onItemChecked(it, item) },
            )
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = item.text,
            )
        }
    }
}