package com.danielwaiguru.tripitacaandroid.shared.models

import android.net.Uri

data class User(
    val id: String?,
    val displayName: String?,
    val email: String?,
    val photoUrl: Uri?
)
