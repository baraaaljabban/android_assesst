package com.tawk.github_users.features.github_users.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.tawk.github_users.features.common.Loader
import com.tawk.github_users.features.github_users.presentation.vm.UsersViewModel

@Composable
fun PagingListScreen() {
    val viewModel = hiltViewModel<UsersViewModel>()

    val users = viewModel.fetchPagingUsers().collectAsLazyPagingItems()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            LazyColumn {
                items(
                    count = users.itemCount,
                    key = users.itemKey(key = { it.toString() }
                    ),
                    contentType = users.itemContentType(
                    )
                ) { index ->
                    val items = users[index]

                    for (item in items!!.indices) {
                        UserItem(items[item], item)
                    }
                    Divider()
                }

                when (val state = users.loadState.refresh) {
                    is LoadState.Error -> {
                        item {
                            state.error.message?.let { Text(text = it) }
                         }
                    }

                    is LoadState.Loading -> {
                        item {
                            Loader()
                        }
                    }

                    else -> {}
                }

                when (val state = users.loadState.append) {
                    is LoadState.Error -> {
                        item {
                            state.error.message?.let { Text(text = it) }
                        }
                    }

                    is LoadState.Loading -> {
                        item {
                            Loader()
                        }
                    }

                    else -> {}
                }
            }
        }
}
