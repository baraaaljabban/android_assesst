package com.tawk.github_users.features.users_details.domain.use_case

import com.tawk.github_users.features.user_deatils.domain.repository.UserDetailsRepository
import javax.inject.Inject


interface SaveNote {
    suspend fun saveNote(note: String, localId: Int)
    class Base @Inject constructor(private val userDetailsRepository: UserDetailsRepository) :
        SaveNote {
        override suspend fun saveNote(note: String, localId: Int) {
            userDetailsRepository.saveNote(note, localId)
        }

    }
}