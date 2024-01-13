package com.danielwaiguru.tripitacaandroid.auth.data.sources.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TripitacaDataStore @Inject constructor(
    private val userPrefs: DataStore<Preferences>
) {
    suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        userPrefs.edit { mutablePreferences ->
            mutablePreferences[key] = value
        }
    }

    fun <T> get(key: Preferences.Key<T>, default: T): Flow<T> {
        return userPrefs.data.map { preferences ->
            preferences[key] ?: default
        }
    }
}