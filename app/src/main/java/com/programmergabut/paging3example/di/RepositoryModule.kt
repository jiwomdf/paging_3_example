package com.programmergabut.paging3example.di

import com.programmergabut.paging3example.data.remote.UsersRepository
import com.programmergabut.paging3example.data.remote.UsersRepositoryImpl
import com.programmergabut.paging3example.data.remote.service.GithubUsersService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(githubUsersService: GithubUsersService): UsersRepository {
        return UsersRepositoryImpl(githubUsersService)
    }

}