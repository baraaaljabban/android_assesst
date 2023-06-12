package com.tawk.github_users.features.github_users.domain.use_case

import com.tawk.github_users.features.github_users.domain.enities.User
import com.tawk.github_users.features.github_users.domain.repository.GithubUsersRepository
import javax.inject.Inject


interface SearchUsers {
    suspend  fun searchGithubUsers(query: String): List<User>

    class Base @Inject constructor(
        private val githubUsersRepository: GithubUsersRepository,
    ) : SearchUsers {

        override suspend fun searchGithubUsers(query: String): List<User> {
            return githubUsersRepository.searchGithubUsers(query)
        }
    }

}
