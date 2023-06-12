package com.tawk.github_users.features.github_users.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawk.github_users.features.github_users.data.model.UserModel

@Dao
interface GithubUsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubUsers(githubUsers:List<UserModel>)

    @Query("select * from github_users")
    fun getAllGithubUsers(): List<UserModel>


    @Query("SELECT note FROM github_users WHERE id = :userId")
    suspend fun getUserNoteById(userId: Int): String?

    @Query("UPDATE github_users SET note = :note WHERE localId = :localId")
    suspend fun updateNoteForUser(localId: Int, note: String)

    @Query("SELECT * FROM github_users WHERE login LIKE '%' || :query || '%' OR note LIKE '%' || :query || '%' COLLATE NOCASE")
    suspend fun searchUsers(query: String): List<UserModel>


}