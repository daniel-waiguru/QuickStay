package com.danielwaiguru.datastore.contract

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface QuickStayDataStore {
    suspend fun <T> set(key: Preferences.Key<T>, value: T)

    fun <T> get(key: Preferences.Key<T>, default: T): Flow<T>
}