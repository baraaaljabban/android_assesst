package com.tawk.github_users.features.github_users.data.data_source.remote

import com.tawk.github_users.features.github_users.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubUsersRemoteDataSource {

    @GET("users")
    suspend fun getGithubUsers(
        @Query("since") since: Int,
    ): UserResponse
}