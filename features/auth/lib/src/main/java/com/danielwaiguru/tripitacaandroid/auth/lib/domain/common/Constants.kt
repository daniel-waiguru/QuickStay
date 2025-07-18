package com.danielwaiguru.tripitacaandroid.auth.lib.domain.common

import androidx.datastore.preferences.core.stringPreferencesKey

internal object Constants {
    val USERNAME_KEY = stringPreferencesKey("signed_in_username")
}