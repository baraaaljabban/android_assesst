package com.tawk.github_users.features.github_users.presentation.widgets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.tawk.github_users.features.github_users.presentation.vm.UserSearchResult
import com.tawk.github_users.features.github_users.presentation.vm.UsersViewModel

@Composable
fun SearchResultScreen(query: String) {
    val viewModel= hiltViewModel<UsersViewModel>()
    LaunchedEffect(query) {
        viewModel.searchUsers(query)
    }
    val searchResultsState = viewModel.searchResults.collectAsState()

    when (val searchResult = searchResultsState.value) {
        is UserSearchResult.Success -> {
            if (searchResult.users.isEmpty()) {
                Text(text = "No data found")
            } else {
                LazyColumn {
                    items(searchResult.users.size) { user ->
                        UserItem(searchResult.users[user], user)
                        Divider()
                    }
                }
            }
        }

        is UserSearchResult.Error -> {
            Text(text = "Error: ${searchResult.message}")
        }
    }
}