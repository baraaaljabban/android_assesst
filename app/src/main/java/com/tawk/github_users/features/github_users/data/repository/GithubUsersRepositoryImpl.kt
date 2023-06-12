package com.tawk.github_users.features.github_users.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tawk.github_users.features.github_users.data.data_source.local.GithubUsersDao
import com.tawk.github_users.features.github_users.data.data_source.remote.GithubUsersRemoteDataSource
import com.tawk.github_users.features.github_users.data.model.toUser
import com.tawk.github_users.features.github_users.domain.enities.User
import com.tawk.github_users.features.github_users.domain.repository.GithubUsersRepository
import javax.inject.Inject


/**
 * Implementation of the GithubUsersRepository.
 *
 * @param apiService The remote data source for fetching Github users.
 * @param githubUsersDao The local data source for accessing cached Github users.
 */
class GithubUsersRepositoryImpl @Inject constructor(
    private val apiService: GithubUsersRemoteDataSource,
    private val githubUsersDao: GithubUsersDao
) : GithubUsersRepository() {

    /**
     * Retrieves saved Github users from the local data source.
     *
     * @return A list of saved Github users.
     */
    override suspend fun getSavedGithubUsers(): List<User> {
        val result = githubUsersDao.getAllGithubUsers()
        return ((result.map { it.toUser() }))
    }

    /**
     * Searches Github users based on the provided query.
     *
     * @param query The search query, this will be either the user name or the note.
     * @return A list of Github users matching the search query from local data base.
     */
    override suspend fun searchGithubUsers(query: String): List<User> {
        val result = githubUsersDao.searchUsers(query)
        return result.map { it.toUser() }
    }

    /**
     * Retrieves paged Github users using the paging library.
     *
     * @return A Flow of paged Github users.
     */
    override fun getPaginGithubUsers() = Pager(
        config = PagingConfig(
            pageSize = 1,
        ),
        pagingSourceFactory = {
            GithubUsersPagingSource(apiService, githubUsersDao)
        }
    ).flow
}
