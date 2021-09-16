package com.jesusrojo.composedemo.state.examples.start

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.jesusrojo.composedemo.state.todo.TodoItem
import com.jesusrojo.composedemo.state.ui.StateCodelabTheme

class TodoActivityStart : AppCompatActivity() {

    val todoViewModel by viewModels<TodoViewModelStart>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateCodelabTheme {
                Surface {
                    // T0DO: build the screen in compose
                    TodoActivityStartScreen(todoViewModel)

                }
            }
        }
    }

    //This composable will be a bridge between the state stored in our ViewModel
    // and the TodoScreenStart composable that's already defined in the project.
    @Composable
    private fun TodoActivityStartScreen(todoViewModel: TodoViewModelStart) {
        // val items = listOf<TodoItem>() // in the next steps we'll complete this
        val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
        TodoScreenStart(
            items = items,
            //onAddItem = {}, //in the next steps we'll complete this
            //onRemoveItem = {} // in the next steps we'll complete this
            onAddItem = { todoViewModel.addItem(it) },
            onRemoveItem = todoViewModel::removeItem // SAME AS BEFORE, method reference syntax
        )
    }
}
