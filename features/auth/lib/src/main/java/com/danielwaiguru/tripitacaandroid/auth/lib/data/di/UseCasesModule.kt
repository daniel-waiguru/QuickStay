package com.danielwaiguru.tripitacaandroid.auth.lib.data.di

import com.danielwaiguru.auth.contract.GetUserUseCase
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.usecases.GetUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UseCasesModule {
    @Binds
    @Singleton
    internal abstract fun bindGetUserUseCase(
        getUserUseCaseImpl: GetUserUseCaseImpl
    ): GetUserUseCase
}