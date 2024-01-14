package com.danielwaiguru.tripitacaandroid.shared.state

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

@Stable
sealed class ViewState<out T> {

    data class Success<out T>(val data: T) : ViewState<T>()

    data object Loading : ViewState<Nothing>()

    data class Error(
        val message: String? = null,
        @StringRes val messageRes: Int? = null
    ) : ViewState<Nothing>()
}