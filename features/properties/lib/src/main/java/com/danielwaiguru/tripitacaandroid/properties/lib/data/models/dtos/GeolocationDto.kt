package com.danielwaiguru.tripitacaandroid.properties.lib.data.models.dtos


import com.danielwaiguru.properties.contract.models.GeoLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class GeolocationDto(
    @SerialName("lat")
    override val latitude: Double,
    @SerialName("lon")
    override val longitude: Double,
): GeoLocation