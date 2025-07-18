package com.danielwaiguru.datastore.lib.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.danielwaiguru.datastore.contract.QuickStayDataStore
import com.danielwaiguru.datastore.lib.QuickStayDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataStoreModule {
    @Binds
    @Singleton
    internal abstract fun bindQuickStayDataStore(
        quickStayDataStoreImpl: QuickStayDataStoreImpl
    ): QuickStayDataStore

    companion object {
        @Provides
        @Singleton
        internal fun providesDataStorePreferences(
            @ApplicationContext appContext: Context
        ): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler(
                    produceNewData = { emptyPreferences() }
                ),
                scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
                produceFile = {
                    appContext.preferencesDataStoreFile("com.danielwaiguru.quickstay.datastore")
                }
            )
        }
    }
}