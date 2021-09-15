package com.jesusrojo.composedemo.uidata.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jesusrojo.composedemo.state.todo.*
import com.jesusrojo.composedemo.uidata.data.fake.FakeData
import com.jesusrojo.composedemo.uidata.data.model.UiData
import com.jesusrojo.composedemo.uidata.data.model.UiDataIcon
import kotlin.random.Random

/**
 * Stateless component that is responsible for the entire todo screen.
 *
 * @param items (state) list of [UiData] to display
 * @param currentlyEditing (state) enable edit mode for this item
 * @param onAddItem (event) request an item be added
 * @param onRemoveItem (event) request an item be removed
 * @param onStartEdit (event) request an item start edit mode
 * @param onEditItemChange (event) request the current edit item be updated
 * @param onEditDone (event) request edit mode completion
 */
@Composable
fun UiDataScreen(
    items: List<UiData>,
    currentlyEditing: UiData?,
    onAddItem: (UiData) -> Unit,
    onRemoveItem: (UiData) -> Unit,
    onStartEdit: (UiData) -> Unit,
    onEditItemChange: (UiData) -> Unit,
    onEditDone: () -> Unit
) {
    Column {
        val enableTopSection = currentlyEditing == null
        UiDataInputBackground(elevate = enableTopSection) {
            if (enableTopSection) {
                UiDataEntryInput(onAddItem)
            } else {
                Text(
                    text = "Editing item",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items = items) { uiData ->
                if (currentlyEditing?.id == uiData.id) {
                    UiDataInlineEditor(
                        data = currentlyEditing,
                        onEditItemChange = onEditItemChange,
                        onEditDone = onEditDone,
                        onRemoveItem = { onRemoveItem(uiData) }
                    )
                } else {
                    UiDataRow(
                        data = uiData,
                        onItemClicked = { onStartEdit(it) },
                        modifier = Modifier.fillParentMaxWidth()
                    )
                }
            }
        }

        // For quick testing, a random item generator button
        Button(
            onClick = { onAddItem(FakeData.generateRandomUiData()) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text("Add random item")
        }
    }
}

/**
 * Stateless composable that provides a styled [UiDataInput] for inline editing.
 *
 * This composable will display a floppy disk and ❌ for the buttons.
 *
 * @param data (state) the current item to display in editor
 * @param onEditItemChange (event) request item be changed
 * @param onEditDone (event) request edit mode completion for this item
 * @param onRemoveItem (event) request this item be removed
 */
@Composable
fun UiDataInlineEditor(
    data: UiData,
    onEditItemChange: (UiData) -> Unit,
    onEditDone: () -> Unit,
    onRemoveItem: () -> Unit
) {
    UiDataInput(
        text = data.title,
        onTextChange = { onEditItemChange(data.copy(title = it)) },
        icon = data.icon,
        onIconChange = { onEditItemChange(data.copy(icon = it)) },
        submit = onEditDone,
        iconsVisible = true,
        buttonSlot = {
            Row {
                val shrinkButtons = Modifier.widthIn(20.dp)
                TextButton(onClick = onEditDone, modifier = shrinkButtons) {
                    Text(
                        "\uD83D\uDCBE", // floppy disk
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(30.dp)
                    )
                }
                TextButton(onClick = onRemoveItem, modifier = shrinkButtons) {
                    Text(
                        "❌",
                        textAlign = TextAlign.End,
                        modifier = Modifier.width(30.dp)
                    )
                }
            }
        }
    )
}

/**
 * Stateful composable to allow entry of *new* [UiData].
 *
 * This composable will display a button with [buttonText].
 *
 * @param onItemComplete (event) notify the caller that the user has completed entry of an item
 * @param buttonText text to display on the button
 */
@Composable
fun UiDataEntryInput(onItemComplete: (UiData) -> Unit, buttonText: String = "Add") {
    val (text, onTextChange) = rememberSaveable { mutableStateOf("") }
    val (icon, onIconChange) = remember { mutableStateOf(UiDataIcon.Default) }

    val submit = {
        if (text.isNotBlank()) {
            onItemComplete(UiData(text, icon))
            onTextChange("")
            onIconChange(UiDataIcon.Default)
        }
    }

    UiDataInput(
        text = text,
        onTextChange = onTextChange,
        icon = icon,
        onIconChange = onIconChange,
        submit = submit,
        iconsVisible = text.isNotBlank()
    ) {
        UiDataEditButton(onClick = submit, text = buttonText, enabled = text.isNotBlank())
    }
}

/**
 * Stateless input composable for editing [UiData].
 *
 * @param text (state) current text of the item
 * @param onTextChange (event) request the text change
 * @param icon (state) current selected icon for the item
 * @param onIconChange (event) request the current icon change
 * @param submit (event) notify the caller that the user has submitted with [ImeAction.Done]
 * @param iconsVisible (state) display icons or hide them
 * @param buttonSlot (slot) slot for providing buttons next to the text
 */
@Composable
fun UiDataInput(
    text: String,
    onTextChange: (String) -> Unit,
    icon: UiDataIcon,
    onIconChange: (UiDataIcon) -> Unit,
    submit: () -> Unit,
    iconsVisible: Boolean,
    buttonSlot: @Composable () -> Unit,
) {
    Column {
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .height(IntrinsicSize.Min)
        ) {
            UiDataInputText(
                text = text,
                onTextChange = onTextChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onImeAction = submit
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(Modifier.align(Alignment.CenterVertically)) { buttonSlot() }
        }
        if (iconsVisible) {
            AnimatedIconRow(
                icon = icon,
                onIconChange = onIconChange,
                modifier = Modifier.padding(top = 8.dp),
            )
        } else {
            Spacer(Modifier.height(16.dp))
        }
    }
}

/**
 * Stateless composable that displays a full-width [UiData].
 *
 * @param data item to show
 * @param onItemClicked (event) notify caller that the row was clicked
 * @param modifier modifier for this element
 * @param iconAlpha alpha tint to apply to icon, by default random between 0.3 and 0.9
 */
@Composable
fun UiDataRow(
    data: UiData,
    onItemClicked: (UiData) -> Unit,
    modifier: Modifier = Modifier,
    iconAlpha: Float = remember(data.id) { randomTint() }
) {
    Row(
        modifier
            .clickable { onItemClicked(data) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(data.title)
        Icon(
            imageVector = data.icon.imageVector,
            tint = LocalContentColor.current.copy(alpha = iconAlpha),
            contentDescription = stringResource(id = data.icon.contentDescription)
        )
    }
}

private fun randomTint(): Float {
    return Random.nextFloat().coerceIn(0.3f, 0.9f)
}

@Preview
@Composable
fun PreviewUiDataScreen() {
    val datas = listOf(
        UiData("Learn compose", UiDataIcon.Event),
        UiData("Take the codelab"),
        UiData("Apply state", UiDataIcon.Done),
        UiData("Build dynamic UIs", UiDataIcon.Square)
    )
    UiDataScreen(datas, null, {}, {}, {}, {}, {})
}

@Preview
@Composable
fun PreviewUiDataInput() = UiDataEntryInput(onItemComplete = { })

@Preview
@Composable
fun PreviewUiDataRow() {
    val todo = remember { FakeData.generateRandomUiData() }
    UiDataRow(data = todo, onItemClicked = {}, modifier = Modifier.fillMaxWidth())
}
