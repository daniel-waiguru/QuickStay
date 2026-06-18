package com.danielwaiguru.tripitacaandroid.auth.lib.data.repositories

import app.cash.turbine.test
import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.tripitacaandroid.auth.lib.data.repositories.fakes.FakeQuickStayDataStore
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test

class UserDataRepositoryImplTest {

    private val json = Json { ignoreUnknownKeys = true }
    private lateinit var dataStore: FakeQuickStayDataStore
    private lateinit var repository: UserDataRepositoryImpl

    private val user = User(
        id = "1",
        displayName = "Daniel",
        email = "daniel@example.com",
        photoUrl = "https://example.com/avatar.png",
    )

    @Before
    fun setUp() {
        dataStore = FakeQuickStayDataStore()
        repository = UserDataRepositoryImpl(dataStore, json)
    }

    @Test
    fun `saveUser returns success`() = runTest {
        val result = repository.saveUser(user)

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `saved user is emitted by getUser`() = runTest {
        repository.saveUser(user)

        repository.getUser().test {
            assertThat(awaitItem()).isEqualTo(user)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getUser emits null when nothing is stored`() = runTest {
        repository.getUser().test {
            assertThat(awaitItem()).isNull()
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `saveUser returns failure when the data store throws`() = runTest {
        dataStore.shouldThrowOnSet = true

        val result = repository.saveUser(user)

        assertThat(result.isFailure).isTrue()
    }
}