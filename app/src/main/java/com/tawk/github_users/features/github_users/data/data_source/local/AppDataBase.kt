package com.tawk.github_users.features.github_users.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tawk.github_users.features.github_users.data.model.UserModel

@Database(entities = [UserModel::class],version = 9,exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
     abstract  fun githubUsersDao(): GithubUsersDao
}

