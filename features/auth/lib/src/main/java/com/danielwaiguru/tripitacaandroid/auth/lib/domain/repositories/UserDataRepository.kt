package com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories

import com.danielwaiguru.auth.contract.models.User
import kotlinx.coroutines.flow.Flow

internal interface UserDataRepository {
    suspend fun saveUser(username: User): Result<Unit>

    fun getUserName(): Flow<User?>
}