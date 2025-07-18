package com.danielwaiguru.auth.contract.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String?,
    val displayName: String?,
    val email: String?,
    val photoUrl: String?
)
