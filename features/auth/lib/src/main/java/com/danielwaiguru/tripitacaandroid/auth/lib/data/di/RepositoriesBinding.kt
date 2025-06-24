package com.danielwaiguru.tripitacaandroid.auth.lib.data.di

import com.danielwaiguru.tripitacaandroid.auth.lib.data.repositories.UserDataRepositoryImpl
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories.UserDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoriesBinding {
    @[Binds Singleton]
    internal abstract fun bindUserDataRepository(
       userDataRepositoryImpl: UserDataRepositoryImpl
    ): UserDataRepository
}