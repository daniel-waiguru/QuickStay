package com.danielwaiguru.tripitacaandroid.properties.data.models.dtos


import com.danielwaiguru.tripitacaandroid.shared.models.GeoLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeolocationDto(
    @SerialName("lat")
    override val latitude: Double,
    @SerialName("lon")
    override val longitude: Double,
): GeoLocation