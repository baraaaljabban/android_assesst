package com.tawk.github_users.features.github_users.domain.enities


open class User(
    var avatarURL: String,
    var userLocalId: Int,
    var loginName: String,
    var note: String?,
) {
    fun hasNote(): Boolean =   !note.isNullOrEmpty()
}