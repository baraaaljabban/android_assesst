package com.tawk.github_users.features.github_users.domain.repository

import androidx.paging.PagingData
import com.tawk.github_users.features.github_users.domain.enities.User
import kotlinx.coroutines.flow.Flow


abstract class GithubUsersRepository {

    /**
     * Retrieves saved Github users.
     *
     * @return A list of saved Github users from local data base.
     */
    abstract suspend fun getSavedGithubUsers(): List<User>

    /**
     * Retrieves paged Github users.
     *
     * @return A Flow of paged Github users from either local data base or remote.
     */
    abstract fun getPaginGithubUsers(): Flow<PagingData<List<User>>>

    /**
     * Searches Github users based on the provided query.
     *
     * @param query The search query, this will be either the user name or the note.
     * @return A list of Github users matching the search query from local data base.
     */
    abstract suspend fun searchGithubUsers(query: String): List<User>

}