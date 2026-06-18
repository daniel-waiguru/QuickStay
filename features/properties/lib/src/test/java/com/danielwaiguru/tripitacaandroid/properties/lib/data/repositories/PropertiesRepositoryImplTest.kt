package com.danielwaiguru.tripitacaandroid.properties.lib.data.repositories

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class PropertiesRepositoryImplTest {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }
    private lateinit var repository: PropertiesRepositoryImpl

    @Before
    fun setUp() {
        repository = PropertiesRepositoryImpl(
            ioDispatcher = UnconfinedTestDispatcher(),
            context = RuntimeEnvironment.getApplication(),
            kotlinxJson = json,
        )
    }

    @Test
    fun `getListing parses the bundled listings asset`() = runTest {
        val result = repository.getListing()

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrThrow()).isNotEmpty()
    }

    @Test
    fun `getFindPropertyById returns the matching property`() = runTest {
        val anyId = repository.getListing().getOrThrow().first().id

        val result = repository.getFindPropertyById(anyId)

        assertThat(result.isSuccess).isTrue()
        assertThat(result.getOrThrow().id).isEqualTo(anyId)
    }

    @Test
    fun `getFindPropertyById returns failure for an unknown id`() = runTest {
        val result = repository.getFindPropertyById("non-existent-id")

        assertThat(result.isFailure).isTrue()
    }
}