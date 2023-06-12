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
)

