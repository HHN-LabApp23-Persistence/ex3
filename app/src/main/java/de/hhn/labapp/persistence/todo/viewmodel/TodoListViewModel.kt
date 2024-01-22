package de.hhn.labapp.persistence.todo.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.room.Room
import de.hhn.labapp.persistence.todo.model.AppDatabase
import de.hhn.labapp.persistence.todo.model.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TodoListViewModel(context: Context) : ViewModel() {
    val todoItems = mutableStateListOf(*createSampleData().toTypedArray())
        @Synchronized get
    var currentItemText by mutableStateOf("")
    var currentItem by mutableStateOf<TodoItem?>(null)

    private val database: AppDatabase = buildDatabase(context)
    private val databaseScope = CoroutineScope(Dispatchers.IO)

    val showDialog: Boolean
        get() = currentItem != null

    val isSaveEnabled: Boolean
        get() = currentItemText.isNotBlank()

    init {
        loadItems()
    }

    fun withDatabase(block: AppDatabase.() -> Unit): Job {
        return databaseScope.launch {
            val res = database.block()
            Log.d("Todo items in database", database.todoItemDao().getAll().toString())
            return@launch res
        }
    }

    fun search(query: String) {
        withDatabase {
            val items = todoItemDao().searchByText(query)

            synchronized(todoItems) {
                todoItems.clear()
                todoItems.addAll(items)
            }
        }
    }

    fun onItemChecked(newValue: Boolean, item: TodoItem) {
        val index = todoItems.indexOf(item)
        val copy = item.copy(id = item.id, completed = newValue)
        withDatabase {
            todoItemDao().update(copy)
            todoItems[index] = copy
        }
    }

    fun onItemClicked(item: TodoItem) {
        currentItem = item
    }

    fun deleteItem(item: TodoItem): Boolean {
        todoItems.remove(item)
        withDatabase { todoItemDao().delete(item) }
        return true
    }

    fun onAddItem() {
        val item = TodoItem(text = "")
        todoItems.add(item)
        currentItem = item
    }

    fun onSaveItem() {
        if (currentItem == null) {
            return
        }

        val item = currentItem!!.copy(id=currentItem!!.id, text = currentItemText)
        val index = todoItems.indexOf(currentItem!!)
        withDatabase {
            todoItemDao().upsert(item)
            todoItems[index] = item
        }
    }

    private fun loadItems() {
        withDatabase {
            val items = todoItemDao().getAll()

            synchronized(todoItems) {
                todoItems.clear()
                todoItems.addAll(items)
            }
        }
    }

    private fun createSampleData(): List<TodoItem> {
        return listOf(
            TodoItem(text = "Buy milk", completed = true),
            TodoItem(text = "Buy eggs"),
            TodoItem(text = "Buy bread"),
        )
    }

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo-list"
        ).build()
    }
}