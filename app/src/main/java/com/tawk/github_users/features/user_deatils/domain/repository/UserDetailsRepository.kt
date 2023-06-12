package com.tawk.github_users.features.user_deatils.domain.repository

import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails


abstract class UserDetailsRepository {
    /**
     * Fetches the user details for the given [username] and [localId].
     *
     * @param username The username of the user.
     * @param localId The local ID of the user, and this will be needed to check if the user
     * has note or not.
     * @return The user details for the given username.
     */
    abstract suspend fun getUserDetails(username: String, localId: Int): UserDetails
    /**
     * Saves or updates the [note] for the user with the given [localId].
     *
     * @param note The note to be saved.
     * @param localId The local ID of the user.
     */
    abstract suspend fun saveNote(note: String, localId: Int)
}