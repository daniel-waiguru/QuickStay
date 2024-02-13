package com.danielwaiguru.tripitacaandroid.properties.data.models.dtos


import androidx.annotation.Keep
import com.danielwaiguru.tripitacaandroid.shared.models.GeoLocation
import com.google.gson.annotations.SerializedName
@Keep
data class GeolocationDto(
    @SerializedName("lat")
    override val latitude: Double,
    @SerializedName("lon")
    override val longitude: Double,
): GeoLocation