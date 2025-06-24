package com.danielwaiguru.auth.contract

import com.danielwaiguru.auth.contract.models.User
import kotlinx.coroutines.flow.Flow

interface GetUserUseCase {
    fun getUser(): Flow<User?>
}