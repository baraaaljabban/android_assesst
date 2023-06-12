package com.tawk.github_users.features.user_deatils.domain.use_case

import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails
import com.tawk.github_users.features.user_deatils.domain.repository.UserDetailsRepository
import javax.inject.Inject

/**
 * Interface for fetching user details for a given username and local ID.
 */
interface FetchUserDetails {
    /**
     * Fetches the user details for the given [username] and [localId].
     *
     * @param username The username of the user.
     * @param localId The local ID of the user, and this will be needed to check if the user
     * has note or not.
     * @return The user details for the given username.
     */
    suspend fun fetchUserDetails(username: String, localId: Int): UserDetails
    class Base @Inject constructor(private val userDetailsRepository: UserDetailsRepository) :
        FetchUserDetails {
        /**
         * Fetches the user details for the given [username] and [localId].
         *
         * @param username The username of the user.
         * @param localId The local ID of the user, and this will be needed to check if the user
         * has note or not.
         * @return The user details for the given username.
         */
        override suspend fun fetchUserDetails(username: String, localId: Int): UserDetails {
            return userDetailsRepository.getUserDetails(username = username, localId)
        }

    }
}