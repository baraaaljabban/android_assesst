package com.tawk.github_users.features.github_users.presentation.vm

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tawk.github_users.core.connectivity.ConnectivityMonitor
import com.tawk.github_users.core.connectivity.ConnectivityViewModel
import com.tawk.github_users.features.github_users.domain.enities.User
import com.tawk.github_users.features.github_users.domain.use_case.FetchGithubUsers
import com.tawk.github_users.features.github_users.domain.use_case.SearchUsers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val fetchUsersUseCase: FetchGithubUsers,
    connectivityMonitor: ConnectivityMonitor,
    private val searchUsers: SearchUsers
) : ConnectivityViewModel(connectivityMonitor) {
    // Flow of paginated user data
    var usersData: Flow<PagingData<List<User>>> = emptyFlow()

    // Flag to track if user data has been fetched
    private var isDataFetched = false

    // StateFlow for search results
    private val _searchResults = MutableStateFlow<UserSearchResult>(
        UserSearchResult.Success(emptyList())
    )
    val searchResults: StateFlow<UserSearchResult> = _searchResults

    fun fetchPagingUsers(): Flow<PagingData<List<User>>> {
        return if (isDataFetched) {
            usersData
        } else {
            runCatching {
                fetchUsersUseCase.getPagingGithubUsers().cachedIn(viewModelScope)
            }.fold(
                onSuccess = { result ->
                    usersData = result
                    isDataFetched = true
                    result
                },
                onFailure = { _ ->
                    usersData = emptyFlow()
                    emptyFlow()
                }
            )
        }
    }


    /**
     * Searches Github users based on the provided query from the app bar search .
     *
     * @param query The search query.
     */
    fun searchUsers(query: String) {
        viewModelScope.launch {
            kotlin.runCatching { searchUsers.searchGithubUsers(query) }.onSuccess {
                _searchResults.value = UserSearchResult.Success(it)
                Log.e("UserSearchResult.Success", it.toString())
            }.onFailure {
                _searchResults.value = UserSearchResult.Error(it.message.toString())
                Log.e("UserSearchResult.Error", it.toString())
            }
        }
    }


    /**
     * Callback method triggered when network connectivity is restored.
     * Fetches paginated users data.
     */
    override fun onConnectivityRestored() {
        fetchPagingUsers()
    }
}

