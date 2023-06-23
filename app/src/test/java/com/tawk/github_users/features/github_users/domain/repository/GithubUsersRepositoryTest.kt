package com.tawk.github_users.features.github_users.domain.repository

import com.tawk.github_users.features.github_users.data.data_source.local.GithubUsersDao
import com.tawk.github_users.features.github_users.data.data_source.remote.GithubUsersRemoteDataSource
import com.tawk.github_users.features.github_users.data.model.UserModel
import com.tawk.github_users.features.github_users.data.repository.GithubUsersRepositoryImpl
import com.tawk.github_users.features.github_users.domain.enities.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GithubUsersRepositoryTest {

    @Mock
    private lateinit var apiService: GithubUsersRemoteDataSource

    @Mock
    private lateinit var githubUsersDao: GithubUsersDao

    private lateinit var repository: GithubUsersRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = GithubUsersRepositoryImpl(apiService, githubUsersDao)
    }

    @Test
    fun getSavedGithubUsers_returnsListOfUsers() = runBlocking {
        // Arrange
        val u1 = UserModel(id = 1, login = "john", localId = 1, avatar_url = null, note = null)
        val u2 = UserModel(id = 2, login = "jane", localId = 2, avatar_url = null, note = null)

        val userModels = listOf(u1, u2)
        val u3 = User(userLocalId = 1, loginName = "john", avatarURL = "", note = "")
        val u4 = User(userLocalId = 2, loginName = "jane", avatarURL = "", note = "")

        val expectedUsers = listOf(
            u3, u4
        )
        Mockito.`when`(githubUsersDao.getAllGithubUsers()).thenReturn(userModels)

        // Act
        val result = repository.getSavedGithubUsers()

        // Assert
        assertEquals(expectedUsers.size, result.size)
        assertEquals(expectedUsers.hashCode(), result.hashCode())

    }

    @Test
    fun searchGithubUsers_returnsMatchingUsers() = runBlocking {
        // Arrange
        val query = "john"
        val userModels = listOf(
            UserModel(id = 1, login = "john", localId = 1, avatar_url = null, note = null),
        )
        val expectedUsers = listOf(
            User(userLocalId = 1, loginName = "john", avatarURL = "", note = "")

        )
        Mockito.`when`(githubUsersDao.getAllGithubUsers()).thenReturn(userModels)

        // Act
        val result = repository.searchGithubUsers(query)

        // Assert

        assertEquals(expectedUsers, result)
    }
}