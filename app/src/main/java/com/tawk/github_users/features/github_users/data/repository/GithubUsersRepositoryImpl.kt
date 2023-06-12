package com.tawk.github_users.features.github_users.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tawk.github_users.features.github_users.data.data_source.local.GithubUsersDao
import com.tawk.github_users.features.github_users.data.data_source.remote.GithubUsersRemoteDataSource
import com.tawk.github_users.features.github_users.data.model.toUser
import com.tawk.github_users.features.github_users.domain.enities.User
import com.tawk.github_users.features.github_users.domain.repository.GithubUsersRepository
import javax.inject.Inject


class GithubUsersRepositoryImpl @Inject constructor(
    private val apiService: GithubUsersRemoteDataSource,
    private val githubUsersDao: GithubUsersDao
) : GithubUsersRepository() {

    override suspend fun getSavedGithubUsers(): List<User> {
        val result = githubUsersDao.getAllGithubUsers()
        return ((result.map { it.toUser() }))
    }

    override suspend fun searchGithubUsers(query: String): List<User> {
        val result = githubUsersDao.searchUsers(query)
        return result.map { it.toUser() }
    }

    override fun getPaginGithubUsers() = Pager(
        config = PagingConfig(
            pageSize = 1,
        ),
        pagingSourceFactory = {
            GithubUsersPagingSource(apiService, githubUsersDao)
        }
    ).flow
}
