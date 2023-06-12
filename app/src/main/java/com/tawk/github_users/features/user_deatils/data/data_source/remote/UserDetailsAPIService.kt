package com.tawk.github_users.features.user_deatils.data.data_source.remote

import com.tawk.github_users.features.user_deatils.data.model.UserDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path


interface UserDetailsAPIService {
    /**
     * Retrieves the details of a user from the remote data source.
     *
     * @param username The username of the user to fetch details for.
     * @return The [UserDetailsModel] representing the user's details.
     */
    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): UserDetailsModel

}