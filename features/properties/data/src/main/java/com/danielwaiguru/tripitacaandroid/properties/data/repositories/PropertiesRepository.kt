package com.danielwaiguru.tripitacaandroid.properties.data.repositories

import android.content.Context
import com.danielwaiguru.tripitacaandroid.properties.data.mappers.toProperty
import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.PropertyDto
import com.danielwaiguru.tripitacaandroid.properties.data.models.response.PropertiesResponse
import com.danielwaiguru.tripitacaandroid.properties.data.utils.parseJson
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.Dispatcher
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.DispatcherProvider
import com.danielwaiguru.tripitacaandroid.shared.models.Property
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

interface PropertiesRepository {
    suspend fun getListing(): Result<List<Property>>
    suspend fun getFindPropertyById(propertyId: String): Result<Property>
}

internal class PropertiesRepositoryImpl @Inject constructor(
    @Dispatcher(DispatcherProvider.IO) private val ioDispatcher: CoroutineDispatcher,
    @ApplicationContext private val context: Context,
    private val kotlinxJson: Json
): PropertiesRepository {
    override suspend fun getListing(): Result<List<Property>> {
        return try {
            val response = parseJson<PropertiesResponse>(
                path = "listings.json",
                context = context,
                dispatcher = ioDispatcher,
                kotlinxJson = kotlinxJson
            )
            Result.success(response.results.map(PropertyDto::toProperty))
        } catch (e: Exception) {
            e.printStackTrace()
            coroutineContext.ensureActive()
            Result.failure(e)
        }
    }

    override suspend fun getFindPropertyById(propertyId: String): Result<Property> {
        return try {
            val response = parseJson<PropertiesResponse>(
                path = "listings.json",
                context = context,
                dispatcher = ioDispatcher,
                kotlinxJson = kotlinxJson
            )
            val property = response.results.find {
                it.id == propertyId
            }!!
            Result.success(property.toProperty())
        } catch (e: Exception) {
            e.printStackTrace()
            coroutineContext.ensureActive()
            Result.failure(e)
        }
    }
}