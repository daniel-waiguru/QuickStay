package com.danielwaiguru.tripitacaandroid.properties.data.models.response

import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.PropertyDto
import com.google.gson.annotations.SerializedName

data class PropertiesResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val results: List<PropertyDto>
)
