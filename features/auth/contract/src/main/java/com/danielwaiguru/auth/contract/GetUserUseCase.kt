package com.danielwaiguru.auth.contract

import com.danielwaiguru.auth.contract.models.User
import kotlinx.coroutines.flow.Flow

fun interface GetUserUseCase {
    operator fun invoke(): Flow<User?>
}