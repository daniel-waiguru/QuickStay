package com.danielwaiguru.tripicaandroid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Named(DispatcherProvider.IO)
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
object DispatcherProvider {
    const val IO = "IO"
    const val MAIN = "MAIN"
}