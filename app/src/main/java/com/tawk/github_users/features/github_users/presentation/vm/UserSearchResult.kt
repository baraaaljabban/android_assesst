package com.tawk.github_users.features.github_users.presentation.vm

import com.tawk.github_users.features.github_users.domain.enities.User

sealed class UserSearchResult {
    data class Success(val users: List<User>) : UserSearchResult()
    data class Error(val message: String) : UserSearchResult()
}