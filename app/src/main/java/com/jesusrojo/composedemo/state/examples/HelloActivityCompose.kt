package com.jesusrojo.composedemo.state.examples

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel


class HelloActivityCompose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloScreen()
           // HelloScreenWithInternalState()
        }
    }
}

@Composable
private fun HelloScreen(helloViewModel: HelloViewModel = viewModel()) {
    // helloViewModel follows the Lifecycle as the the Activity or Fragment that calls this
    // composable function.

    // name is the _current_ value of [helloViewModel.name]
    val name: String by helloViewModel.name.observeAsState("")

    HelloInput(name = name, onNameChange = { helloViewModel.onNameChanged(it) })
}

@Composable
private fun HelloScreenWithInternalState() {
    val (name, setName) = remember { mutableStateOf("") }
    HelloInput(name = name, onNameChange = setName)
}



/**
 * @param name (state) current text to display
 * @param onNameChange (event) request that text change
 */
@Composable
private fun HelloInput(
    name: String,
    onNameChange: (String) -> Unit
) {
    Column {
        Text("HelloActivityCompose", color = Color.Red)
        Text(name)
        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Please, enter your name") }
        )
    }
}