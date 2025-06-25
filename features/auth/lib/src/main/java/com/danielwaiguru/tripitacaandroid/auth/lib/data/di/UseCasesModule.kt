package com.danielwaiguru.tripitacaandroid.auth.lib.data.di

import com.danielwaiguru.auth.contract.GetUserUseCase
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories.UserDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    internal fun provideGetUserUseCase(
        userDataRepository: UserDataRepository
    ): GetUserUseCase = GetUserUseCase {
        userDataRepository.getUser()
    }
}