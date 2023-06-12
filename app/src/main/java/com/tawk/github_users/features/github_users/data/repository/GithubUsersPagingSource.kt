package com.tawk.github_users.features.github_users.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tawk.github_users.features.github_users.data.data_source.local.GithubUsersDao
import com.tawk.github_users.features.github_users.data.data_source.remote.GithubUsersRemoteDataSource
import com.tawk.github_users.features.github_users.data.model.UserModel
import com.tawk.github_users.features.github_users.data.model.toUser
import com.tawk.github_users.features.github_users.domain.enities.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GithubUsersPagingSource(
    private val remoteDataSource: GithubUsersRemoteDataSource,
    private val githubUsersDao: GithubUsersDao

) : PagingSource<Int, List<User>>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, List<User>> {
        try {
            val page = params.key ?: 0
            val localUsers = withContext(Dispatchers.IO) {
                githubUsersDao.getAllGithubUsers()
            }
            val remoteUsers = withContext(Dispatchers.IO) {
                remoteDataSource.getGithubUsers(page)
            }

            val newUsers = remoteUsers.filter { remoteUser ->
                localUsers.none { localUser -> localUser.id == remoteUser.id }
            }.distinctBy { it.id }

            withContext(Dispatchers.IO) {
                launch {
                    githubUsersDao.insertGithubUsers(newUsers)
                }
            }

            val finalResult: List<UserModel> = localUsers.ifEmpty { remoteUsers }

            return LoadResult.Page(
                data = listOf(finalResult.map { it.toUser() }),
                prevKey = null,
                nextKey = if (remoteUsers.isEmpty()) null else page.plus(
                    remoteUsers.last().id ?: 1
                ),
            )
        } catch (e: Exception) {
            throw e
        }
    }

    override fun getRefreshKey(state: PagingState<Int, List<User>>): Int =
        ((state.anchorPosition ?: 0) - state.config.initialLoadSize / 2).coerceAtLeast(0)
}
