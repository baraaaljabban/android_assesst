package com.tawk.github_users.features.github_users.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "github_users")
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val localId: Int? = null,
    var avatar_url: String? = "",
    val id: Int?,
    val login: String?,
    val note: String?
) {
    override fun hashCode(): Int {
        var result = avatar_url?.toString()?.hashCode() ?: 0
        result = 31 * result + (localId ?: 0)
        result = 31 * result + (login?.hashCode() ?: 0)
        result = 31 * result + (note?.hashCode() ?: 0)
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserModel

        if (localId != other.localId) return false
        if (avatar_url != other.avatar_url) return false
        if (id != other.id) return false
        if (login != other.login) return false
        if (note != other.note) return false

        return true
    }
}

