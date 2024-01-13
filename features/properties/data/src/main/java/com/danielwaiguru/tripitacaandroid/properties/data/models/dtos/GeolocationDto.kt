package com.danielwaiguru.tripitacaandroid.properties.data.models.dtos


import com.google.gson.annotations.SerializedName

data class GeolocationDto(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)