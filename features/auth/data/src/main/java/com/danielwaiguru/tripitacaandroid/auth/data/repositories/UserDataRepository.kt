package com.danielwaiguru.tripitacaandroid.auth.data.repositories

import com.danielwaiguru.tripitacaandroid.auth.data.sources.local.TripitacaDataStore
import com.danielwaiguru.tripitacaandroid.shared.contants.SharedConstants.USERNAME_KEY
import javax.inject.Inject

interface UserDataRepository {
    suspend fun saveUser(username: String): Result<Unit>
}

internal class UserDataRepositoryImpl @Inject constructor(
    private val tripitacaDataStore: TripitacaDataStore
): UserDataRepository {
    override suspend fun saveUser(username: String): Result<Unit> = try {
        Result.success(tripitacaDataStore.set(USERNAME_KEY, username))
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure(e)
    }
}