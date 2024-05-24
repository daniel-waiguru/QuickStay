package com.danielwaiguru.tripitacaandroid.properties.data.models.response

import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.PropertyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertiesResponse(
    @SerialName("total_count")
    val totalCount: Int,
    val results: List<PropertyDto>
)
