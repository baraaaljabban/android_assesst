package com.tawk.github_users.features.github_users.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tawk.github_users.features.github_users.data.model.UserModel

/**
 * Data Access Object (DAO) interface for the Github users table.
 */
@Dao
interface GithubUsersDao {
    /**
     * Inserts a list of Github users into the table. If a user already exists, it will be replaced.
     *
     * @param githubUsers The list of Github users to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubUsers(githubUsers:List<UserModel>)

    /**
     * Retrieves all Github users from the table.
     *
     * @return The list of all Github users.
     */
    @Query("select * from github_users")
    fun getAllGithubUsers(): List<UserModel>


    /**
     * Retrieves the note associated with a user by their ID.
     *
     * @param userId The ID of the user.
     * @return The note associated with the user, or null if no note exists.
     */
    @Query("SELECT note FROM github_users WHERE id = :userId")
    suspend fun getUserNoteById(userId: Int): String?

    /**
     * Updates the note for a user with the specified local ID.
     *
     * @param localId The local ID of the user.
     * @param note The new note to set for the user.
     */
    @Query("UPDATE github_users SET note = :note WHERE localId = :localId")
    suspend fun updateNoteForUser(localId: Int, note: String)

    /**
     * Searches for Github users based on a query string. The query matches the user's login or note.
     *
     * @param query The search query.
     * @return The list of Github users matching the search query.
     */
    @Query("SELECT * FROM github_users WHERE login LIKE '%' || :query || '%' OR note LIKE '%' || :query || '%' COLLATE NOCASE")
    suspend fun searchUsers(query: String): List<UserModel>


}