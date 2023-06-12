package com.tawk.github_users.features.user_deatils.domain.repository

import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails


abstract class UserDetailsRepository {
    abstract suspend fun getUserDetails(username: String, localId: Int): UserDetails
    abstract suspend fun saveNote(note: String, localId: Int)
}