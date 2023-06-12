package com.tawk.github_users.features.user_deatils.domain.use_case

import com.tawk.github_users.features.user_deatils.domain.repository.UserDetailsRepository
import javax.inject.Inject


interface SaveNote {
    /**
     * Saves the [note] for the user identified by the [localId].
     *
     * @param note The note to be saved.
     * @param localId The local ID of the user.
     */
    suspend fun saveNote(note: String, localId: Int)
    class Base @Inject constructor(private val userDetailsRepository: UserDetailsRepository) :
        SaveNote {

        /**
         * Saves the [note] for the user identified by the [localId] by delegating to the [UserDetailsRepository].
         *
         * @param note The note to be saved.
         * @param localId The local ID of the user.
         */
        override suspend fun saveNote(note: String, localId: Int) {
            userDetailsRepository.saveNote(note, localId)
        }

    }
}