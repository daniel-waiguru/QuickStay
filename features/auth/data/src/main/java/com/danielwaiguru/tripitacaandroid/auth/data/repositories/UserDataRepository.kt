package com.danielwaiguru.tripitacaandroid.auth.data.repositories

import com.danielwaiguru.tripitacaandroid.auth.data.sources.local.TripitacaDataStore
import com.danielwaiguru.tripitacaandroid.shared.contants.SharedConstants.USERNAME_KEY
import com.danielwaiguru.tripitacaandroid.shared.models.User
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface UserDataRepository {
    suspend fun saveUser(username: User): Result<Unit>

    fun getUserName(): Flow<User?>
}

internal class UserDataRepositoryImpl @Inject constructor(
    private val tripitacaDataStore: TripitacaDataStore
): UserDataRepository {
    override suspend fun saveUser(username: User): Result<Unit> = try {
        Result.success(tripitacaDataStore.set(USERNAME_KEY, Gson().toJson(username)))
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
                    Gson().fromJson(it, User::class.java)
                }
            }
    }
}