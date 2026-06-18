package com.danielwaiguru.tripitacaandroid.properties.lib.data.mappers

import com.danielwaiguru.tripitacaandroid.properties.lib.data.models.dtos.GeolocationDto
import com.danielwaiguru.tripitacaandroid.properties.lib.data.models.dtos.PropertyDto
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PropertyMappersTest {

    @Test
    fun `maps core identity and location fields`() {
        val dto = propertyDto(
            id = "42",
            name = "Sunny Loft",
            geolocation = GeolocationDto(latitude = 1.5, longitude = 2.5),
        )

        val property = dto.toProperty()

        assertThat(property.id).isEqualTo("42")
        assertThat(property.name).isEqualTo("Sunny Loft")
        assertThat(property.location.latitude).isEqualTo(1.5)
        assertThat(property.location.longitude).isEqualTo(2.5)
    }

    @Test
    fun `review score rating is scaled to a five point scale`() {
        // (90 * 5) / 100 using integer arithmetic == 4
        val property = propertyDto(reviewScoresRating = 90).toProperty()

        assertThat(property.reviewScoresRating).isEqualTo(4.0)
    }

    @Test
    fun `null review score rating maps to zero`() {
        val property = propertyDto(reviewScoresRating = null).toProperty()

        assertThat(property.reviewScoresRating).isEqualTo(0.0)
    }

    @Test
    fun `amenities with missing translations are filtered out`() {
        val property = propertyDto(
            amenities = listOf("TV", "translation missing: en.hosting_amenity", "Wifi"),
        ).toProperty()

        assertThat(property.amenities).containsExactly("TV", "Wifi").inOrder()
    }

    @Test
    fun `null amenities map to an empty list`() {
        val property = propertyDto(amenities = null).toProperty()

        assertThat(property.amenities).isEmpty()
    }

    @Test
    fun `cancellation policy is capitalised`() {
        val property = propertyDto(cancellationPolicy = "moderate").toProperty()

        assertThat(property.cancellationPolicy).isEqualTo("Moderate")
    }

    @Test
    fun `host since date is reformatted`() {
        val property = propertyDto(hostSince = "2009-11-21").toProperty()

        assertThat(property.hostSince).isEqualTo("21 Nov, 2009")
    }

    private fun propertyDto(
        id: String = "1",
        name: String = "Property",
        amenities: List<String>? = emptyList(),
        cancellationPolicy: String = "flexible",
        reviewScoresRating: Int? = null,
        hostSince: String = "2015-01-01",
        geolocation: GeolocationDto = GeolocationDto(latitude = 0.0, longitude = 0.0),
    ): PropertyDto = PropertyDto(
        accommodates = 2,
        amenities = amenities,
        bathrooms = 1,
        bedType = "Real Bed",
        bedrooms = 1,
        beds = 1,
        bookedDates = emptyList(),
        calculatedHostListingsCount = 1,
        cancellationPolicy = cancellationPolicy,
        city = "Nairobi",
        country = "Kenya",
        countryCode = "KE",
        description = "A lovely place",
        experiencesOffered = "none",
        extraPeople = 0,
        geolocation = geolocation,
        guestsIncluded = 1,
        hostId = "host-1",
        hostListingsCount = 1,
        hostLocation = "Nairobi",
        hostName = "Jane",
        hostPictureUrl = "https://example.com/host.png",
        hostSince = hostSince,
        hostThumbnailUrl = "https://example.com/thumb.png",
        hostTotalListingsCount = 1,
        hostVerifications = listOf("email"),
        id = id,
        latitude = "0.0",
        longitude = "0.0",
        market = "Nairobi",
        maximumNights = 30,
        minimumNights = 1,
        name = name,
        neighbourhoodCleansed = "Central",
        neighbourhoodGroupCleansed = "Central",
        numberOfReviews = 0,
        photos = listOf("https://example.com/photo.png"),
        price = 100,
        propertyType = "Apartment",
        reviewScoresRating = reviewScoresRating,
        roomType = "Entire home/apt",
        smartLocation = "Nairobi, Kenya",
        state = "Nairobi",
        street = "Some street",
    )
}