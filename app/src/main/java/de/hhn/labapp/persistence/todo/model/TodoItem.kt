package de.hhn.labapp.persistence.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_item")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,

    var text: String,
    var completed: Boolean = false
)
