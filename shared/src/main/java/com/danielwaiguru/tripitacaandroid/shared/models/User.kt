package com.danielwaiguru.tripitacaandroid.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String?,
    val displayName: String?,
    val email: String?,
    val photoUrl: String?
)
