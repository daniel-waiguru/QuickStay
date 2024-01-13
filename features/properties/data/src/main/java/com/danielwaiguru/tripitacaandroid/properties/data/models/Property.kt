package com.danielwaiguru.tripitacaandroid.properties.data.models

data class Property(
    val id: String,
    val location: GeoLocation,
    val description: String,
    val hostName: String,
    val hostPictureUrl: String,
    val name: String,
    val photos: List<String>,
    val price: Int,
    val propertyType: String,
    val roomType: String,
    val reviewScoresRating: Int,
    val numberOfReviews: Int?,
    val reviewsPerMonth: Double?,
    val city: String,
    val country: String,
    val amenities: List<String>,
    val cancellationPolicy: String,
    val hostSince: String,
    val bookedDates: List<String>,
    val beds: Int,
    val bathrooms: Int,
    val guestsIncluded: Int,
    val hostAbout: String?,
    val isFavourite: Boolean = false
) {
    data class GeoLocation(
        val latitude: Double,
        val longitude: Double
    )
}