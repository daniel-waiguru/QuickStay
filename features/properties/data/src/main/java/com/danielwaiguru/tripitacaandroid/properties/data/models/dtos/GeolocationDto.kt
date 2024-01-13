package com.danielwaiguru.tripitacaandroid.properties.data.models.dtos


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class GeolocationDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)