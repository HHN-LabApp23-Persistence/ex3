package de.hhn.labapp.persistence.todo.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_item")
    fun getAll(): List<TodoItem>

    @Query("SELECT * FROM todo_item WHERE id = :id")
    fun get(id: Int): TodoItem?

    @Update
    fun update(todoItem: TodoItem)

    @Upsert
    fun upsert(todoItem: TodoItem)

    @Delete
    fun delete(todoItem: TodoItem)

    @Query("SELECT * FROM todo_item WHERE text LIKE '%' || :query || '%'")
    fun searchByText(query: String): List<TodoItem>
}
