package com.danielwaiguru.tripitacaandroid.properties.lib.data.di

import com.danielwaiguru.properties.contract.GetPropertyUseCase
import com.danielwaiguru.tripitacaandroid.properties.lib.domain.repositories.PropertiesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {
    @Provides
    @Singleton
    internal fun provideGetPropertyUseCase(
        propertiesRepository: PropertiesRepository
    ): GetPropertyUseCase = GetPropertyUseCase { propertyId ->
        propertiesRepository.getFindPropertyById(propertyId)
    }
}