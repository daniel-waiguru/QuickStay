package com.danielwaiguru.tripitacaandroid.auth.lib.data.repositories

import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.tripitacaandroid.auth.lib.data.sources.local.QuickStayDataStore
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.common.Constants.USERNAME_KEY
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject

internal class UserDataRepositoryImpl @Inject constructor(
    private val quickStayDataStore: QuickStayDataStore,
    private val kotlinxJson: Json
): UserDataRepository {
    override suspend fun saveUser(username: User): Result<Unit> = try {
        Result.success(quickStayDataStore.set(USERNAME_KEY, kotlinxJson.encodeToString(username)))
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }

    override fun getUser(): Flow<User?> {
        return quickStayDataStore.get(USERNAME_KEY, "")
            .map {
                if (it.isBlank()) {
                    null
                } else {
                    kotlinxJson.decodeFromString(it)
                }
            }
    }
}