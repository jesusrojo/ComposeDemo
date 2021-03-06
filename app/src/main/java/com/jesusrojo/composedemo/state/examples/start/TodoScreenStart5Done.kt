//package com.jesusrojo.composedemo.state.examples.start
//
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.Button
//import androidx.compose.material.Icon
//import androidx.compose.material.LocalContentColor
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.jesusrojo.composedemo.state.todo.TodoIcon
//import com.jesusrojo.composedemo.state.todo.TodoItem
//import com.jesusrojo.composedemo.state.util.generateRandomTodoItem
//import kotlin.random.Random
//
///**
// * Stateless component that is responsible for the entire todo screen.
// *
// * @param items (state) list of [TodoItem] to display
// * @param onAddItem (event) request an item be added
// * @param onRemoveItem (event) request an item be removed
// */
//
////https://developer.android.com/codelabs/jetpack-compose-state#3
//@Composable
//fun TodoScreenStart(
//    items: List<TodoItem>,
//    onAddItem: (TodoItem) -> Unit,
//    onRemoveItem: (TodoItem) -> Unit
//) {
//    Column {
//        LazyColumn(
//            modifier = Modifier.weight(1f),
//            contentPadding = PaddingValues(top = 8.dp)
//        ) {
//            items(items = items) {
//                TodoRow(
//                    todo = it,
//                    onItemClicked = { onRemoveItem(it) },
//                    modifier = Modifier.fillParentMaxWidth()
//                )
//            }
//        }
//
//        // For quick testing, a random item generator button
//        Button(
//            onClick = { onAddItem(generateRandomTodoItem()) },
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//        ) {
//            Text("Add random item")
//        }
//    }
//}
//
///**
// * Stateless composable that displays a full-width [TodoItem].
// *
// * @param todo item to show
// * @param onItemClicked (event) notify caller that the row was clicked
// * @param modifier modifier for this element
// */
//@Composable
//fun TodoRow(todo: TodoItem,
//            onItemClicked: (TodoItem) -> Unit,
//            modifier: Modifier = Modifier,
//            iconAlpha: Float = remember(todo.id) { randomTint() }/*step5-3/3*/) {
//    Row(
//        modifier = modifier
//            .clickable { onItemClicked(todo) }
//            .padding(horizontal = 16.dp, vertical = 8.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        Text(todo.task)
//        //val iconAlpha = randomTint() //step5-1/3
//        //val iconAlpha: Float = remember(todo.id) { randomTint() } //step5-2/3
//        Icon(
//            imageVector = todo.icon.imageVector,
//            tint = LocalContentColor.current.copy(alpha = iconAlpha),//step5
//            contentDescription = stringResource(id = todo.icon.contentDescription)
//        )
//    }
//}
//
//private fun randomTint(): Float {
//    return Random.nextFloat().coerceIn(0.3f, 0.9f)
//}
//
//@Preview
//@Composable
//fun PreviewTodoScreen() {
//    val items = listOf(
//        TodoItem("Learn compose", TodoIcon.Event),
//        TodoItem("Take the codelab"),
//        TodoItem("Apply state", TodoIcon.Done),
//        TodoItem("Build dynamic UIs", TodoIcon.Square)
//    )
//    TodoScreenStart(items, {}, {})
//}
//
//@Preview
//@Composable
//fun PreviewTodoRow() {
//    val todo = remember { generateRandomTodoItem() }
//    TodoRow(todo = todo, onItemClicked = {}, modifier = Modifier.fillMaxWidth())
//}
