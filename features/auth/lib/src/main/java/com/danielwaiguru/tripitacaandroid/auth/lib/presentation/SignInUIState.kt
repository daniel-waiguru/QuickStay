package com.danielwaiguru.tripitacaandroid.auth.lib.presentation

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable

@Stable
internal data class SignInUIState(
    val email: String = "",
    val password: String = "",
    @StringRes val errorId: Int? = null,
    val isLoggedIn: Boolean = false,
    val isLoading: Boolean = false
)
