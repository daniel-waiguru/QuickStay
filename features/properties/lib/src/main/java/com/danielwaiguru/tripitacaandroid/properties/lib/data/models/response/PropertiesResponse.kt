package com.danielwaiguru.tripitacaandroid.properties.lib.data.models.response

import com.danielwaiguru.tripitacaandroid.properties.lib.data.models.dtos.PropertyDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PropertiesResponse(
    @SerialName("total_count")
    val totalCount: Int,
    val results: List<PropertyDto>
)
