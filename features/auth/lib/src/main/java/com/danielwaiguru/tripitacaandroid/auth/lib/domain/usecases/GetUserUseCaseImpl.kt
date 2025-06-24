package com.danielwaiguru.tripitacaandroid.auth.lib.domain.usecases

import com.danielwaiguru.auth.contract.GetUserUseCase
import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories.UserDataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GetUserUseCaseImpl @Inject constructor(
    private val userDataRepository: UserDataRepository
): GetUserUseCase {
    override fun getUser(): Flow<User?> = userDataRepository.getUserName()
}