package com.danielwaiguru.tripitacaandroid.properties.data.models.response

import androidx.annotation.Keep
import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.PropertyDto
import com.google.gson.annotations.SerializedName
@Keep
data class PropertiesResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    val results: List<PropertyDto>
)
