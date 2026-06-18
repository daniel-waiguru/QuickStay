package com.danielwaiguru.tripitacaandroid.auth.lib.data.repositories.fakes

import androidx.datastore.preferences.core.Preferences
import com.danielwaiguru.datastore.contract.QuickStayDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

/**
 * In-memory [QuickStayDataStore] fake backed by a [MutableStateFlow] map.
 * Set [shouldThrowOnSet] to simulate a write failure.
 */
internal class FakeQuickStayDataStore : QuickStayDataStore {
    private val preferences = MutableStateFlow<Map<Preferences.Key<*>, Any?>>(emptyMap())

    var shouldThrowOnSet: Boolean = false

    override suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        if (shouldThrowOnSet) throw IllegalStateException("Unable to persist value")
        preferences.update { current -> current.toMutableMap().apply { put(key, value) } }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> get(key: Preferences.Key<T>, default: T): Flow<T> =
        preferences.map { current -> (current[key] as? T) ?: default }
}