package com.danielwaiguru.tripitacaandroid.properties.lib.domain.repositories

import com.danielwaiguru.properties.contract.models.Property

internal interface PropertiesRepository {
    suspend fun getListing(): Result<List<Property>>
    suspend fun getFindPropertyById(propertyId: String): Result<Property>
}