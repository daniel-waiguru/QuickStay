
package com.danielwaiguru.tripitacaandroid.shared.di

import com.danielwaiguru.tripitacaandroid.shared.dispatchers.Dispatcher
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @[
        Provides
        Dispatcher(DispatcherProvider.IO)
    ]
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @[
        Provides
        Dispatcher(DispatcherProvider.MAIN)
    ]
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
