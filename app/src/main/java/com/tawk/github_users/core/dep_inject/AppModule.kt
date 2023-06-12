package com.tawk.github_users.core.dep_inject

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tawk.github_users.core.connectivity.ConnectivityInterceptor
import com.tawk.github_users.core.connectivity.ConnectivityInterceptorImpl
import com.tawk.github_users.core.connectivity.ConnectivityMonitor
import com.tawk.github_users.core.notework.retrofitBuilder
import com.tawk.github_users.features.github_users.data.data_source.local.AppDataBase
import com.tawk.github_users.features.github_users.data.data_source.local.GithubUsersDao
import com.tawk.github_users.features.github_users.data.data_source.remote.GithubUsersRemoteDataSource
import com.tawk.github_users.features.github_users.data.repository.GithubUsersRepositoryImpl
import com.tawk.github_users.features.github_users.domain.repository.GithubUsersRepository
import com.tawk.github_users.features.github_users.domain.use_case.FetchGithubUsers
import com.tawk.github_users.features.github_users.domain.use_case.SearchUsers
import com.tawk.github_users.features.user_deatils.data.data_source.remote.UserDetailsAPIService
import com.tawk.github_users.features.user_deatils.data.repository.UserDetailsRepositoryImpl
import com.tawk.github_users.features.user_deatils.domain.repository.UserDetailsRepository
import com.tawk.github_users.features.user_deatils.domain.use_case.FetchUserDetails
import com.tawk.github_users.features.user_deatils.domain.use_case.SaveNote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideConnectivityInterceptor(@ApplicationContext appContext: Context): ConnectivityInterceptor {
        return ConnectivityInterceptorImpl(appContext)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideConnectivityMonitor(
        connectivityManager: ConnectivityManager
    ): ConnectivityMonitor {
        return ConnectivityMonitor(connectivityManager)
    }

    @Singleton
    @Provides
    fun provideRetrofit(connectivityInterceptor: ConnectivityInterceptor): Retrofit {
        return retrofitBuilder(connectivityInterceptor)
    }


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideRemoteDataService(retrofit: Retrofit): GithubUsersRemoteDataSource =
        retrofit.create(GithubUsersRemoteDataSource::class.java)

    @Provides
    fun provideUserDetailsAPIService(retrofit: Retrofit): UserDetailsAPIService =
        retrofit.create(UserDetailsAPIService::class.java)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(
            appContext, AppDataBase::class.java, "RssReader"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideGithubUsersDao(appDatabase: AppDataBase): GithubUsersDao {
        return appDatabase.githubUsersDao()
    }


    @Provides
    @Singleton
    fun provideGithubUsersRepository(
        nyTimesApiService: GithubUsersRemoteDataSource,
        githubUsersDao: GithubUsersDao,
    ): GithubUsersRepository = GithubUsersRepositoryImpl(
        nyTimesApiService,
        githubUsersDao,
    )

    @Provides
    fun provideFetchGithubUsers(githubUsersRepository: GithubUsersRepository): FetchGithubUsers {
        return FetchGithubUsers.Base(githubUsersRepository)
    }

    @Provides
    fun provideSearchUsers(githubUsersRepository: GithubUsersRepository): SearchUsers {
        return SearchUsers.Base(githubUsersRepository)
    }
    @Provides
    fun provideFetchUserDetails(userDetailsRepository: UserDetailsRepository): FetchUserDetails {
        return FetchUserDetails.Base(userDetailsRepository)
    }

    @Provides
    fun provideSaveNote(userDetailsRepository: UserDetailsRepository): SaveNote {
        return SaveNote.Base(userDetailsRepository)
    }

    @Provides
    @Singleton
    fun provideUserDetailsRepository(
        userDetailsAPIService: UserDetailsAPIService,
        githubUsersDao: GithubUsersDao
    ): UserDetailsRepository =
        UserDetailsRepositoryImpl(userDetailsAPIService, githubUsersDao)


}