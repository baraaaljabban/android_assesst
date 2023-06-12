package com.tawk.github_users.features.user_deatils.data.model

import com.tawk.github_users.features.user_deatils.domain.enities.UserDetails

fun UserDetailsModel.toUserDetails(note: String = ""): UserDetails {
    return UserDetails(
        avatar_url = avatar_url,
        bio = bio,
        blog = blog,
        company = company,
        followers = followers,
        following = following,
        login = login,
        name = name, note = note
    )
}
