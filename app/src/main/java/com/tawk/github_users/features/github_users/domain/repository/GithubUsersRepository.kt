package com.tawk.github_users.features.github_users.domain.repository

import androidx.paging.PagingData
import com.tawk.github_users.features.github_users.domain.enities.User
import kotlinx.coroutines.flow.Flow


abstract class GithubUsersRepository {

    abstract suspend fun getSavedGithubUsers(): List<User>

    abstract fun getPaginGithubUsers(): Flow<PagingData<List<User>>>
    abstract suspend fun searchGithubUsers(query: String): List<User>

}