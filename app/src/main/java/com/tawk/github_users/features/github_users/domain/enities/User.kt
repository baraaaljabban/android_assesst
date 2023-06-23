package com.tawk.github_users.features.github_users.domain.enities


open class User(
    var avatarURL: String,
    var userLocalId: Int,
    var loginName: String,
    var note: String?,
) {
    fun hasNote(): Boolean = !note.isNullOrEmpty()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (userLocalId != other.userLocalId) return false
        if (loginName != other.loginName) return false
        if (avatarURL != other.avatarURL) return false
        if (note != other.note) return false

        return true
    }
    fun copy(): User {
        return User(avatarURL, userLocalId, loginName, note)
    }
    override fun hashCode(): Int {
        var result = avatarURL.hashCode() ?: 0
        result = 31 * result + (userLocalId ?: 0)
        result = 31 * result + (loginName.hashCode() ?: 0)
        result = 31 * result + (note?.hashCode() ?: 0)
        return result

    }

}