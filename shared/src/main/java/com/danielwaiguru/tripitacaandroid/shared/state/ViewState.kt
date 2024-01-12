package com.danielwaiguru.tripitacaandroid.shared.state

import androidx.annotation.StringRes

sealed class ViewState {

    data class Success<out T>(val data: T) : ViewState()

    data object Loading : ViewState()

    data class Error(
        val message: String? = null,
        @StringRes val messageRes: Int? = null
    ) : ViewState()
}