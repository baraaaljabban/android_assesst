package com.tawk.github_users.features.github_users.data.model

import com.tawk.github_users.features.github_users.domain.enities.User

fun UserModel.toUser(): User {
    return User(
        avatarURL = this.avatar_url ?: "",
        userLocalId = this.localId ?: 1,
        loginName = this.login ?: "",
        note = this.note,
    )
}
