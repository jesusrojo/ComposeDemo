package com.jesusrojo.composedemo.uidata.presentation.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import com.jesusrojo.composedemo.uidata.presentation.ui.theme.UiDataTheme
import com.jesusrojo.composedemo.uidata.presentation.viewmodel.UiDataViewModel

class UiDataActivity : AppCompatActivity() {

    private val viewModel by viewModels<UiDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UiDataTheme() {
                Surface {
                    UiDataActivityScreen(viewModel)
                }
            }
        }
    }
}

@Composable
private fun UiDataActivityScreen(viewModel: UiDataViewModel) {
    UiDataScreen(
        items = viewModel.uiDatas,
        currentlyEditing = viewModel.currentEditItem,
        onAddItem = viewModel::addItem,
        onRemoveItem = viewModel::removeItem,
        onStartEdit = viewModel::onEditItemSelected,
        onEditItemChange = viewModel::onEditItemChange,
        onEditDone = viewModel::onEditDone
    )
}