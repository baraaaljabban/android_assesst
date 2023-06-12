package com.tawk.github_users.features.user_deatils.data.data_source.remote

import com.tawk.github_users.features.user_deatils.data.model.UserDetailsModel
import retrofit2.http.GET
import retrofit2.http.Path


interface UserDetailsAPIService {
    @GET("users/{username}")
    suspend fun getUserDetails(@Path("username") username: String): UserDetailsModel

}