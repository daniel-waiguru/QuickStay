package com.danielwaiguru.tripicaandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KotlinxJsonModule {
    @Provides
    @Singleton
    fun provideKotlinxJson() = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
}