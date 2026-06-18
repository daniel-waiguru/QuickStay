package com.danielwaiguru.datastore.lib

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import app.cash.turbine.test
import com.danielwaiguru.datastore.contract.QuickStayDataStore
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File

class QuickStayDataStoreImplTest {

    @get:Rule
    val tempFolder = TemporaryFolder()

    private fun createDataStore(produceFile: () -> File): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(produceFile = produceFile)

    private fun newStore(): QuickStayDataStore {
        val dataStore = createDataStore {
            File(tempFolder.newFolder(), "test.preferences_pb")
        }
        return QuickStayDataStoreImpl(dataStore)
    }

    @Test
    fun `set then get returns the stored value`() = runTest {
        val store = newStore()
        val key = stringPreferencesKey("username")

        store.set(key, "Daniel")

        store.get(key, default = "").test {
            assertThat(awaitItem()).isEqualTo("Daniel")
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `get returns the supplied default when the key is absent`() = runTest {
        val store = newStore()
        val key = stringPreferencesKey("missing")

        store.get(key, default = "fallback").test {
            assertThat(awaitItem()).isEqualTo("fallback")
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `overwriting a key emits the latest value`() = runTest {
        val store = newStore()
        val key = intPreferencesKey("count")

        store.set(key, 1)
        store.set(key, 5)

        store.get(key, default = 0).test {
            assertThat(awaitItem()).isEqualTo(5)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `get supports multiple independent typed keys`() = runTest {
        val store = newStore()
        val nameKey = stringPreferencesKey("name")
        val ageKey = intPreferencesKey("age")

        store.set(nameKey, "Ada")
        store.set(ageKey, 36)

        store.get(nameKey, default = "").test {
            assertThat(awaitItem()).isEqualTo("Ada")
            cancelAndConsumeRemainingEvents()
        }
        store.get(ageKey, default = 0).test {
            assertThat(awaitItem()).isEqualTo(36)
            cancelAndConsumeRemainingEvents()
        }
    }
}