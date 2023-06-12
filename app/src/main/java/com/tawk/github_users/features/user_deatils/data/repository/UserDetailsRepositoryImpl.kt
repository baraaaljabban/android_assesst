package com.tawk.github_users.features.user_deatils.data.repository

import com.tawk.github_users.features.github_users.data.data_source.local.GithubUsersDao
import com.tawk.github_users.features.user_deatils.data.data_source.remote.UserDetailsAPIService
import com.tawk.github_users.features.user_deatils.data.model.toUserDetails
import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails
import com.tawk.github_users.features.user_deatils.domain.repository.UserDetailsRepository
import javax.inject.Inject

class UserDetailsRepositoryImpl @Inject constructor(
    private val userDetailsAPIService: UserDetailsAPIService,
    private val githubUsersDao: GithubUsersDao,
) :
    UserDetailsRepository() {
    /**
     * Fetches the user details for the given [username] and [localId].
     *
     * @param username The username of the user.
     * @param localId The local ID of the user, and this will be needed to check if the user
     * has note or not.
     * @return The user details for the given username.
     */
    override suspend fun getUserDetails(username: String, localId: Int): UserDetails {

        return userDetailsAPIService.getUserDetails(username = username)
            .toUserDetails(note = githubUsersDao.getUserNoteById(localId) ?: "")
    }

    /**
     * Saves or updates the [note] for the user with the given [localId].
     *
     * @param note The note to be saved.
     * @param localId The local ID of the user.
     */
    override suspend fun saveNote(note: String, localId: Int) {
        githubUsersDao.updateNoteForUser(localId, note)
    }
}