package com.jesusrojo.composedemo.state.examples

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jesusrojo.composedemo.databinding.ActivityHelloCodelabBinding

/**
 * An example showing unstructured state stored in an Activity.
 */
class HelloCodelabActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelloCodelabBinding
    var name = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHelloCodelabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvMsg.text = javaClass.simpleName.toString()

        // doAfterTextChange is an event that modifies state
        binding.textInput.doAfterTextChanged { text ->
            name = text.toString()
            updateHello()
        }
    }

    /**
     * This function updates the screen to show the current state of [name]
     */
    private fun updateHello() {
        binding.helloText.text = "Hello, $name"
    }
}