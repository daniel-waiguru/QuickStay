package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting

import android.net.Uri
import com.danielwaiguru.tripitacaandroid.shared.models.Property

data class PropertiesUIState(
    val properties: List<Property> = emptyList(),
    val filteredProperties: List<Property> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val username: String = "Anonymous",
    val userProfileUri: Uri? = null,
    val selectedAmenityFilter: String? = null,
    val amenities: List<String> = listOf(
        "TV",
        "Internet",
        "Wireless Internet",
        "Air conditioning",
        "Wheelchair accessible",
        "Kitchen",
        "Free parking on premises",
        "Pets allowed",
        "Elevator in building",
        "Buzzer/wireless intercom",
        "Heating",
        "Family/kid friendly",
        "Suitable for events",
        "Washer",
        "Dryer",
        "Smoke detector",
        "Carbon monoxide detector",
        "First aid kit",
        "Fire extinguisher",
        "Essentials",
        "Shampoo",
        "24-hour check-in",
        "Hangers",
        "Hair dryer",
        "Iron",
        "Laptop friendly workspace"
    )
)
