package com.tawk.github_users.features.github_users.domain.use_case

import com.tawk.github_users.features.github_users.domain.enities.User
import com.tawk.github_users.features.github_users.domain.repository.GithubUsersRepository
import javax.inject.Inject


/**
 * Use Case for searching Github user's note or name and will return a list of users.
 */
interface SearchUsers {
    suspend  fun searchGithubUsers(query: String): List<User>

    class Base @Inject constructor(
        private val githubUsersRepository: GithubUsersRepository,
    ) : SearchUsers {

        /**
         * Searches Github users based on the provided query using the underlying repository.
         *
         * @param query The search query.
         * @return A list of Github users matching the query, it will look for a user name or note.
         */
        override suspend fun searchGithubUsers(query: String): List<User> {
            return githubUsersRepository.searchGithubUsers(query)
        }
    }

}
