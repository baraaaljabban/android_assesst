package com.tawk.github_users.features.github_users.domain.use_case

 import androidx.paging.PagingData
 import com.tawk.github_users.features.github_users.domain.enities.User
 import com.tawk.github_users.features.github_users.domain.repository.GithubUsersRepository
 import kotlinx.coroutines.flow.Flow
 import javax.inject.Inject

interface FetchGithubUsers {
       fun getPagingGithubUsers(): Flow<PagingData<List<User>>>

    class Base @Inject constructor (private val githubUsersRepository: GithubUsersRepository,
    ): FetchGithubUsers {

        override  fun getPagingGithubUsers(): Flow<PagingData<List<User>>> {
            return githubUsersRepository.getPaginGithubUsers()
        }
    }

}
