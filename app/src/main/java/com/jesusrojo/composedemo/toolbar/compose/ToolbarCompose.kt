package com.jesusrojo.composedemo.toolbar.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.jesusrojo.composedemo.R
import com.jesusrojo.composedemo.toolbar.ToolbarTypeVo
import com.jesusrojo.composedemo.toolbar.theme.IGO_STRONG_YELLOW
import com.jesusrojo.composedemo.toolbar.theme.IGO_YELLOW
import com.jesusrojo.composedemo.toolbar.theme.Typography


const val EMPTY_STRING = ""

@Composable
fun ToolbarTitleCompose(
    modifier: Modifier = Modifier,
    title: String,
    type: ToolbarTypeVo = ToolbarTypeVo.Clear,
    onBackCallback: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        val (text, icon) = createRefs()
        if (type !is ToolbarTypeVo.Clear) {
            val idIcon = when (type) {
                is ToolbarTypeVo.Close -> R.drawable.ic_close_pop_up
                else -> R.drawable.ic_arrow_left
            }
            IconButton(
                onClick = onBackCallback,
                content = {
                    Icon(
                        painter = painterResource(id = idIcon),
                        contentDescription = "Arrow back"
                    )
                },
                modifier = Modifier.constrainAs(icon) {
                    start.linkTo(parent.start, 5.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
        Text(
            text = title.toUpperCase(Locale.current),
            modifier = Modifier.constrainAs(text) {
                centerHorizontallyTo(parent)
                centerVerticallyTo(parent)
            },
            style = Typography.kronaRegular(16.sp)
        )
    }
}

@Composable
fun ToolbarTitleWithFilterCompose(
    modifier: Modifier = Modifier,
    title: String,
    onBackCallback: () -> Unit,
    location: String,
    onNotificationCallback: () -> Unit,
    onSearchCallback: () -> Unit,
    onLocationCallback: () -> Unit,
    onFilterCallback: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        ToolbarTitleCompose(title = title, type = ToolbarTypeVo.Back, onBackCallback = onBackCallback)
        ToolbarFilterCompose(
            modifier = modifier.padding(top = 16.dp),
            location = location,
            onNotificationCallback = onNotificationCallback,
            onSearchCallback = onSearchCallback,
            onLocationCallback = onLocationCallback,
            onFilterCallback = onFilterCallback
        )
    }
}

@Composable
private fun ToolbarFilterCompose(
    modifier: Modifier = Modifier,
    location: String,
    onNotificationCallback: () -> Unit,
    onSearchCallback: () -> Unit,
    onLocationCallback: () -> Unit,
    onFilterCallback: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        val (locationText, arrowDown, filter, magnifyingGlass, notification) = createRefs()
        Text(
            text = location,
            modifier = Modifier.constrainAs(locationText) {
                start.linkTo(parent.start, 24.dp)
                centerVerticallyTo(parent)
            },
            style = Typography.robotoRegular(12.sp)
        )
        IconButton(onClick = onLocationCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Location",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(arrowDown) {
            centerVerticallyTo(parent)
            start.linkTo(locationText.end, 2.dp)
        })
        IconButton(onClick = onNotificationCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = "Notifications",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(notification) {
            end.linkTo(parent.end, 16.dp)
            centerVerticallyTo(parent)
        })
        IconButton(onClick = onSearchCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_magnifying_glass),
                contentDescription = "MagnifyingGlass",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(magnifyingGlass) {
            centerVerticallyTo(parent)
            end.linkTo(notification.start, 2.dp)
        })
        IconButton(onClick = onFilterCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = "FilterButton",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(filter) {
            centerVerticallyTo(parent)
            end.linkTo(magnifyingGlass.start, 2.dp)
        })
    }
}

@Composable
fun ToolbarHomeCompose(
    modifier: Modifier = Modifier,
    name: String,
    location: String,
    onNotificationCallback: () -> Unit,
    onSearchCallback: () -> Unit,
    onLocationCallback: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        val (nameText, locationText, arrowDown, magnifyingGlass, notification) = createRefs()
        Text(
            text = name,
            modifier = Modifier.constrainAs(nameText) {
                start.linkTo(parent.start, 24.dp)
                centerVerticallyTo(parent)
            },
            style = Typography.kronaRegular(16.sp)
        )
        IconButton(onClick = onNotificationCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = "Notifications",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(notification) {
            end.linkTo(parent.end, 16.dp)
            centerVerticallyTo(parent)
        })
        IconButton(onClick = onSearchCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_magnifying_glass),
                contentDescription = "MagnifyingGlass",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(magnifyingGlass) {
            centerVerticallyTo(parent)
            end.linkTo(notification.start, 2.dp)
        })
        IconButton(onClick = onLocationCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_down),
                contentDescription = "Location",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(arrowDown) {
            centerVerticallyTo(parent)
            end.linkTo(magnifyingGlass.start, 2.dp)
        })
        Text(
            text = location,
            modifier = Modifier.constrainAs(locationText) {
                end.linkTo(arrowDown.start, 2.dp)
                centerVerticallyTo(parent)
            },
            style = Typography.robotoRegular(12.sp)
        )
    }
}

@Composable
fun ToolbarHomeDeliveryManCompose(
    modifier: Modifier = Modifier,
    name: String,
    imgUrl: String,
    nrNotifications: String,
    onIconCallback: () -> Unit,
    onNotificationCallback: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        val (nameText, imageMoney, profileAvatarPlaceHolder, notification) = createRefs()
        val image: Painter = painterResource(id = R.drawable.ic_vector_money)

        Image(painter = image, contentDescription = "image money",
            modifier = Modifier.size(40.dp, 40.dp)
                .constrainAs(imageMoney) {
                    start.linkTo(parent.start, 24.dp)
                    centerVerticallyTo(parent)
                })

        Text(text = name,
            modifier = Modifier.constrainAs(nameText) {
                start.linkTo(imageMoney.end, 12.dp)
                centerVerticallyTo(parent)
            },
            style = Typography.kronaRegular(16.sp)
        )

        //IMAGE PROFILE
        IconButton(onClick = onIconCallback, content = {
            Icon(
                // painter = painterResource(id = R.drawable.profiledata_avatar_placeholder),
                painter =  rememberImagePainter(
                    data = if(!imgUrl.isNullOrEmpty() ) imgUrl else R.drawable.profiledata_avatar_placeholder,
                    builder = {
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = "image profile",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(profileAvatarPlaceHolder) {
            centerVerticallyTo(parent)
            end.linkTo(notification.start, 2.dp)
        })

        //NOTIFICATION
        IconButton(onClick = onNotificationCallback, content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_notifications),
                contentDescription = "Notifications",
                tint = Color.Unspecified,
            )
        }, modifier = Modifier.constrainAs(notification) {
            end.linkTo(parent.end, 16.dp)
            centerVerticallyTo(parent)
        })
    }
}

@ExperimentalComposeUiApi
@Composable
fun ToolbarSearchCompose(
    modifier: Modifier = Modifier,
    onBackCallback: () -> Unit,
    onSearchCallback: (String) -> Unit,
    onDeleteTextCallback: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        val (iconBack, container) = createRefs()
        IconButton(
            onClick = onBackCallback,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Arrow back"
                )
            },
            modifier = Modifier
                .constrainAs(iconBack) {
                    start.linkTo(parent.start)
                    centerVerticallyTo(parent)
                }
        )
        val focusRequester = FocusRequester()
        var hasFocus by remember { mutableStateOf(false) }
        val modifierBorder = if (hasFocus) {
            Modifier.border(
                width = 3.dp,
                color = IGO_STRONG_YELLOW,
                shape = RoundedCornerShape(60.dp)
            )
        } else {
            Modifier
        }
        ConstraintLayout(
            modifier =
            Modifier
                .fillMaxHeight()
                .constrainAs(container) {
                    start.linkTo(iconBack.end)
                    end.linkTo(parent.end, 40.dp)
                    width = Dimension.fillToConstraints
                }
                .focusRequester(focusRequester)
                .onFocusChanged { hasFocus = it.isFocused }
                .background(
                    color = IGO_YELLOW,
                    shape = RoundedCornerShape(60.dp)
                )
                .then(modifierBorder)

        ) {
            val (editText, searchIcon, deleteIcon) = createRefs()
            var text by rememberSaveable { mutableStateOf(EMPTY_STRING) }

            val focusManager = LocalFocusManager.current

            IconButton(onClick = {
                onSearchCallback(text)
                focusManager.clearFocus()
            },
                modifier = Modifier.constrainAs(searchIcon) {
                    start.linkTo(parent.start, 6.dp)
                    centerVerticallyTo(parent)
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_magnifying_glass),
                    contentDescription = "Magnifying glass",
                    tint = Color.Unspecified
                )
            }

            BasicTextField(
                value = text,
                onValueChange = { textField ->
                    text = textField
                },
                modifier = Modifier
                    .constrainAs(editText) {
                        start.linkTo(searchIcon.end, 3.dp)
                        end.linkTo(parent.end)
                        centerVerticallyTo(parent)
                        centerHorizontallyTo(parent)
                        width = Dimension.preferredWrapContent
                        height = Dimension.preferredWrapContent
                    },
                singleLine = true,
                textStyle = Typography.robotoRegular(14.sp).copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    focusManager.clearFocus()
                    onSearchCallback(text)
                })
            )
            if (text.isNotEmpty()) {
                IconButton(onClick = {
                    text = EMPTY_STRING
                    onDeleteTextCallback.invoke()
                },
                    modifier = Modifier.constrainAs(deleteIcon) {
                        end.linkTo(parent.end, 6.dp)
                        centerVerticallyTo(parent)
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Delete search",
                        tint = Color.Unspecified,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RenderToolbarTitle() {
    ToolbarTitleCompose(title = "Checkout", type = ToolbarTypeVo.Back, onBackCallback = { })
}

@Preview
@Composable
fun RenderToolbarCloseTitle() {
    ToolbarTitleCompose(title = "Filters", type = ToolbarTypeVo.Close, onBackCallback = { })
}

@Preview
@Composable
fun RenderToolbarHome() {
    ToolbarHomeCompose(
        name = "Ola Joao",
        location = "Talatona",
        onNotificationCallback = {},
        onLocationCallback = {},
        onSearchCallback = {})
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun RenderToolbarSearchCompose() {
    ToolbarSearchCompose(
        Modifier, {}, {}, {}
    )
}

@Preview
@Composable
fun RenderToolbarTitleWithFilter() {
    ToolbarTitleWithFilterCompose(
        title = "Comida",
        onBackCallback = { },
        location = "Talatona",
        onNotificationCallback = { },
        onSearchCallback = { },
        onLocationCallback = { }) {
    }
}
