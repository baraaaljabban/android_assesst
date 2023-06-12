package com.tawk.github_users.features.github_users.data.data_source.remote

import com.tawk.github_users.features.github_users.data.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Remote data source interface for fetching Github users from an API.
 */
interface GithubUsersRemoteDataSource {

    /**
     * Retrieves a list of Github users from the API.
     *
     * @param since The ID of the user to start fetching from.
     * @return The response containing the list of Github users.
     */
    @GET("users")
    suspend fun getGithubUsers(
        @Query("since") since: Int,
    ): UserResponse
}