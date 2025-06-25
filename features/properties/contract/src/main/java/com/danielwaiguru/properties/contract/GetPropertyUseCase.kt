package com.danielwaiguru.properties.contract

import com.danielwaiguru.properties.contract.models.Property

fun interface GetPropertyUseCase {
    suspend operator fun invoke(propertyId: String): Result<Property>
}