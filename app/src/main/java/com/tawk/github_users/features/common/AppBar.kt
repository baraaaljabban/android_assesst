package com.tawk.github_users.features.common


import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.tawk.github_users.core.navigator.ROUTE_USER
import com.tawk.github_users.core.navigator.SEARCH_RESULT
import com.tawk.github_users.core.theme.TawkTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppBar(screen: String, navHostController: NavHostController) {

    TawkTheme() {
        TopAppBar(backgroundColor = Color.White) {
            if (screen == ROUTE_USER) {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back", tint = Color.Black,
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 48.dp)
                ) {
                    Text(
                        text = "Name",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                }
            } else if (screen == SEARCH_RESULT) {
                IconButton(onClick = { navHostController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowLeft,
                        contentDescription = "Back", tint = Color.Black,
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 48.dp)
                ) {
                    Text(
                        text = "Name",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center),
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Black
                    )
                }
            } else {
                val searchQueryState = remember { mutableStateOf("") }
                val interactionSource = remember { MutableInteractionSource() }

                BasicTextField(
                    value = searchQueryState.value,
                    onValueChange = { searchQueryState.value = it },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(20.dp)
                        ),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = {

                        val query = searchQueryState.value
                        navHostController.navigate(
                            "searchResult/$query"

                        )

                    }),

                    ) {
                    TextFieldDefaults.TextFieldDecorationBox(
                        value = searchQueryState.value,
                        innerTextField = it,
                        singleLine = true,
                        enabled = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = Color.Black
                            )
                        },
                        visualTransformation = VisualTransformation.None,
                        trailingIcon = {
                            if (searchQueryState.value.isNotEmpty()) {
                                IconButton(
                                    onClick = {
                                        searchQueryState.value = "";

                                    },
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear Search Icon", tint = Color.Black
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        interactionSource = interactionSource,
                        contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                            top = 2.dp, bottom = 2.dp
                        )
                    )
                }

            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun AppBarWithSearch() {
    val searchQueryState = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = searchQueryState.value,
        onValueChange = { searchQueryState.value = it },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(20.dp)
            ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
        }),

        ) {
        TextFieldDefaults.TextFieldDecorationBox(
            value = searchQueryState.value,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            },
            visualTransformation = VisualTransformation.None,
            trailingIcon = {
                if (searchQueryState.value.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            searchQueryState.value = "";

                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Search Icon", tint = Color.Black
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            interactionSource = interactionSource,
            contentPadding = TextFieldDefaults.textFieldWithoutLabelPadding(
                top = 2.dp, bottom = 2.dp
            )
        )
    }

}