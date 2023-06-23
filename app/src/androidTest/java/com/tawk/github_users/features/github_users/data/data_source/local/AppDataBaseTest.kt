package com.tawk.github_users.features.github_users.data.data_source.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tawk.github_users.features.github_users.data.model.UserModel
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDataBaseTest {
    private lateinit var database: AppDataBase
    private lateinit var userDao: GithubUsersDao

    private val user1 = UserModel(
        id = 1, login = "John Doe", localId = 1, avatar_url = null,
        note = null
    )
    private val user2 = UserModel(
        id = 2, login = "Jane Johnson", localId = 2, avatar_url = null,
        note = null
    )
    val users = listOf(
        user1, user2
    )

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDataBase::class.java).build()
        userDao = database.githubUsersDao()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun insertUserAndGetById_returnsExpectedUser() = runTest {
        // Arrange


        // Act
        userDao.insertGithubUsers(users)
        val retrievedUsers = userDao.getAllGithubUsers()

        // Assert
        assertEquals(users.size, retrievedUsers.size)
        assertEquals(users, retrievedUsers)
    }

    @Test
    fun searchUsers_withEmptyQuery_returnsEmptyList() = runTest {
        // Arrange
        val query = ""

        // Act
        val result = userDao.searchUsers(query)

        // Assert
        TestCase.assertTrue(result.isEmpty())
    }

    @Test
    fun searchUsers_withMatchingLogin_returnsMatchingUsers() = runTest {
        // Arrange
        val query = "John"


        userDao.insertGithubUsers(listOf(user1, user2))

        // Act
        val result = userDao.searchUsers(query)

        // Assert
        assertEquals(2, result.size)
        assertEquals(user1, result[0])
        assertEquals(user2, result[1])

    }

    @Test
    fun searchUsers_withMatchingNote_returnsMatchingUsers() = runTest {
        // Arrange
        val query = "important"

        userDao.insertGithubUsers(listOf(user1, user2))

        // Act
        val result = userDao.searchUsers(query)

        // Assert
        assertEquals(0, result.size)
     }

    @Test
    fun searchUsers_withNoMatchingLoginOrNote_returnsEmptyList() = runTest {
        // Arrange
        val query = "test"

        userDao.insertGithubUsers(listOf(user1, user2))

        // Act
        val result = userDao.searchUsers(query)

        // Assert
        TestCase.assertTrue(result.isEmpty())
    }

    @Test
    fun updateNoteForUser_withValidLocalId_updatesNote() = runTest {
        // Arrange
        val localId = 1
        val note = "This is an updated note"

        val user = UserModel(
            id = 1, login = "John Doe", localId = 1, avatar_url = null,
            note = null
        )
        userDao.insertGithubUsers(listOf(user))

        // Act
        userDao.updateNoteForUser(localId, note)
        val updatedUser = userDao.getUserNoteById(localId)

        // Assert
        assertEquals(note, updatedUser)
    }

    @Test
    fun updateNoteForUser_withInvalidLocalId_doesNotUpdateNote() = runTest {
        val updatedUser = userDao.getUserNoteById(3)

        // Assert
        TestCase.assertNull(updatedUser)
    }

}