package com.danielwaiguru.tripitacaandroid.auth.data.repositories

import com.danielwaiguru.tripitacaandroid.auth.data.sources.local.TripitacaDataStore
import com.danielwaiguru.tripitacaandroid.shared.contants.SharedConstants.USERNAME_KEY
import com.danielwaiguru.tripitacaandroid.shared.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface UserDataRepository {
    suspend fun saveUser(username: User): Result<Unit>

    fun getUserName(): Flow<User?>
}

internal class UserDataRepositoryImpl @Inject constructor(
    private val tripitacaDataStore: TripitacaDataStore,
    private val kotlinxJson: Json
): UserDataRepository {
    override suspend fun saveUser(username: User): Result<Unit> = try {
        Result.success(tripitacaDataStore.set(USERNAME_KEY, kotlinxJson.encodeToString(username)))
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }

    override fun getUserName(): Flow<User?> {
        return tripitacaDataStore.get(USERNAME_KEY, "")
            .map {
                if (it.isBlank()) {
                    null
                } else {
                    kotlinxJson.decodeFromString(it)
                }
            }
    }
}