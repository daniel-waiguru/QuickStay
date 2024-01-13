package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting

import com.danielwaiguru.tripitacaandroid.shared.models.Property

data class PropertiesUIState(
    val properties: List<Property> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val username: String? = null
)
