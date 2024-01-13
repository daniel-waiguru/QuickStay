package com.danielwaiguru.tripitacaandroid.properties.data.repositories

import android.content.Context
import com.danielwaiguru.tripitacaandroid.properties.data.mappers.toProperty
import com.danielwaiguru.tripitacaandroid.properties.data.models.Property
import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.PropertyDto
import com.danielwaiguru.tripitacaandroid.properties.data.models.response.PropertiesResponse
import com.danielwaiguru.tripitacaandroid.properties.data.utils.parseJson
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.Dispatcher
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.DispatcherProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

interface PropertiesRepository {
    suspend fun getListing(): Result<List<Property>>
    suspend fun getFindPropertyById(propertyId: String): Result<Property>
}

internal class PropertiesRepositoryImpl @Inject constructor(
    @Dispatcher(DispatcherProvider.IO) private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context
): PropertiesRepository {
    override suspend fun getListing(): Result<List<Property>> {
        return try {
            val response = parseJson<PropertiesResponse>(
                path = "listings.json",
                context = context,
                dispatcher = ioDispatcher
            )
            Result.success(response.results.map(PropertyDto::toProperty))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun getFindPropertyById(propertyId: String): Result<Property> {
        return try {
            val response = parseJson<PropertiesResponse>(
                path = "listings.json",
                context = context,
                dispatcher = ioDispatcher
            )
            val property = response.results.find {
                it.id == propertyId
            }!!
            Result.success(property.toProperty())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}