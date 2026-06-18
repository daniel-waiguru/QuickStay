package com.danielwaiguru.tripitacaandroid.auth.lib.presentation.fakes

import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class FakeUserDataRepository : UserDataRepository {
    private val userFlow = MutableStateFlow<User?>(null)

    /** Result returned by [saveUser]; flip to a failure to drive the error path. */
    var saveResult: Result<Unit> = Result.success(Unit)
    var savedUser: User? = null
        private set

    override suspend fun saveUser(username: User): Result<Unit> {
        if (saveResult.isSuccess) {
            savedUser = username
            userFlow.value = username
        }
        return saveResult
    }

    override fun getUser(): Flow<User?> = userFlow
}