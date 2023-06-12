package com.tawk.github_users.features.user_deatils.domain.enities

data class UserDetails(
    val avatar_url: String?,
    val bio: Any?,
    val blog: String?,
    val company: Any?,
    val followers: Int?,
    val following: Int?,
    val login: String?,
    val name: String?,
    val note: String = ""
)