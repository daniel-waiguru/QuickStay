package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.fakes

import com.danielwaiguru.properties.contract.models.GeoLocation
import com.danielwaiguru.properties.contract.models.Property
import com.danielwaiguru.tripitacaandroid.properties.lib.domain.repositories.PropertiesRepository

internal data class TestGeoLocation(
    override val latitude: Double = 0.0,
    override val longitude: Double = 0.0,
) : GeoLocation

internal fun property(
    id: String = "1",
    name: String = "Property",
    amenities: List<String> = emptyList(),
    isFavourite: Boolean = false,
): Property = Property(
    id = id,
    location = TestGeoLocation(),
    description = "A lovely place",
    hostName = "Jane",
    hostPictureUrl = "https://example.com/host.png",
    name = name,
    photos = listOf("https://example.com/photo.png"),
    price = 100,
    propertyType = "Apartment",
    roomType = "Entire home/apt",
    reviewScoresRating = 4.5,
    numberOfReviews = 10,
    reviewsPerMonth = 1.2,
    city = "Nairobi",
    country = "Kenya",
    amenities = amenities,
    cancellationPolicy = "Flexible",
    hostSince = "1 Jan, 2015",
    bookedDates = emptyList(),
    beds = 1,
    bathrooms = 1,
    guestsIncluded = 1,
    hostAbout = null,
    houseRules = null,
    street = "Some street",
    isFavourite = isFavourite,
)

internal class FakePropertiesRepository : PropertiesRepository {
    var listingResult: Result<List<Property>> = Result.success(emptyList())
    var findResult: Result<Property>? = null

    override suspend fun getListing(): Result<List<Property>> = listingResult

    override suspend fun getFindPropertyById(propertyId: String): Result<Property> =
        findResult ?: listingResult.mapCatching { listing ->
            listing.first { it.id == propertyId }
        }
}