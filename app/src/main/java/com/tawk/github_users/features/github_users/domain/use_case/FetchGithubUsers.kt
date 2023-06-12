package com.tawk.github_users.features.github_users.domain.use_case

import androidx.paging.PagingData
import com.tawk.github_users.features.github_users.domain.enities.User
import com.tawk.github_users.features.github_users.domain.repository.GithubUsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Interface for fetching Github users either from local data base or remote.
 */
interface FetchGithubUsers {
    /**
     * Retrieves paged Github users.
     *
     * @return A Flow of paged Github users.
     */
    fun getPagingGithubUsers(): Flow<PagingData<List<User>>>

    class Base @Inject constructor(
        private val githubUsersRepository: GithubUsersRepository,
    ) : FetchGithubUsers {

        /**
         * Retrieves paged Github users using the underlying repository.
         *
         * @return A Flow of paged Github users.
         */
        override fun getPagingGithubUsers(): Flow<PagingData<List<User>>> {
            return githubUsersRepository.getPaginGithubUsers()
        }
    }

}
