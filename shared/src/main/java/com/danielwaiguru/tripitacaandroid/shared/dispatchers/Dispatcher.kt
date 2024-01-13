
package com.danielwaiguru.tripitacaandroid.shared.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention
annotation class Dispatcher(val dispatcherProvider: DispatcherProvider)

enum class DispatcherProvider {
    IO, MAIN
}
