package com.tawk.github_users.features.users_details.domain.use_case

import com.tawk.github_users.features.users_details.domain.enities.UserDetails
import com.tawk.github_users.features.user_deatils.domain.repository.UserDetailsRepository
import javax.inject.Inject

interface FetchUserDetails {
    suspend fun fetchUserDetails(username: String, localId: Int): UserDetails
    class Base @Inject constructor(private val userDetailsRepository: UserDetailsRepository) :
        FetchUserDetails {
        override suspend fun fetchUserDetails(username: String, localId: Int): UserDetails {
            return userDetailsRepository.getUserDetails(username = username, localId)
        }

    }
}