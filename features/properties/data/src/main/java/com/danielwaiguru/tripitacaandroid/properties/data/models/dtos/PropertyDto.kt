package com.danielwaiguru.tripitacaandroid.properties.data.models.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertyDto(
    @SerialName("access")
    val access: String? = null,
    @SerialName("accommodates")
    val accommodates: Int,
    @SerialName("amenities")
    val amenities: List<String>? = null,
    @SerialName("bathrooms")
    val bathrooms: Int,
    @SerialName("bed_type")
    val bedType: String,
    @SerialName("bedrooms")
    val bedrooms: Int,
    @SerialName("beds")
    val beds: Int,
    @SerialName("booked_dates")
    val bookedDates: List<String>,
    @SerialName("calculated_host_listings_count")
    val calculatedHostListingsCount: Int,
    @SerialName("calendar_updated")
    val calendarUpdated: String? = null,
    @SerialName("cancellation_policy")
    val cancellationPolicy: String,
    @SerialName("city")
    val city: String,
    @SerialName("country")
    val country: String,
    @SerialName("country_code")
    val countryCode: String,
    @SerialName("description")
    val description: String,
    @SerialName("experiences_offered")
    val experiencesOffered: String,
    @SerialName("extra_people")
    val extraPeople: Int,
    @SerialName("first_review")
    val firstReview: String? = null,
    @SerialName("geolocation")
    val geolocation: GeolocationDto,
    @SerialName("guests_included")
    val guestsIncluded: Int,
    @SerialName("host_about")
    val hostAbout: String? = null,
    @SerialName("host_acceptance_rate")
    val hostAcceptanceRate: Int? = null,
    @SerialName("host_id")
    val hostId: String,
    @SerialName("host_listings_count")
    val hostListingsCount: Int,
    @SerialName("host_location")
    val hostLocation: String,
    @SerialName("host_name")
    val hostName: String,
    @SerialName("host_neighbourhood")
    val hostNeighbourhood: String? = null,
    @SerialName("host_picture_url")
    val hostPictureUrl: String,
    @SerialName("host_response_rate")
    val hostResponseRate: Int? = null,
    @SerialName("host_response_time")
    val hostResponseTime: String? = null,
    @SerialName("host_since")
    val hostSince: String,
    @SerialName("host_thumbnail_url")
    val hostThumbnailUrl: String,
    @SerialName("host_total_listings_count")
    val hostTotalListingsCount: Int,
    @SerialName("host_verifications")
    val hostVerifications: List<String>,
    @SerialName("house_rules")
    val houseRules: String? = null,
    @SerialName("id")
    val id: String,
    @SerialName("last_review")
    val lastReview: String? = null,
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String,
    @SerialName("market")
    val market: String,
    @SerialName("maximum_nights")
    val maximumNights: Int,
    @SerialName("minimum_nights")
    val minimumNights: Int,
    @SerialName("monthly_price")
    val monthlyPrice: Int? = null,
    @SerialName("name")
    val name: String,
    @SerialName("neighborhood_overview")
    val neighborhoodOverview: String? = null,
    @SerialName("neighbourhood")
    val neighbourhood: String? = null,
    @SerialName("neighbourhood_cleansed")
    val neighbourhoodCleansed: String,
    @SerialName("neighbourhood_group_cleansed")
    val neighbourhoodGroupCleansed: String,
    @SerialName("notes")
    val notes: String? = null,
    @SerialName("number_of_reviews")
    val numberOfReviews: Int,
    @SerialName("photos")
    val photos: List<String>,
    @SerialName("price")
    val price: Int,
    @SerialName("property_type")
    val propertyType: String,
    @SerialName("review_scores_accuracy")
    val reviewScoresAccuracy: Int? = null,
    @SerialName("review_scores_checkin")
    val reviewScoresCheckin: Int? = null,
    @SerialName("review_scores_cleanliness")
    val reviewScoresCleanliness: Int? = null,
    @SerialName("review_scores_communication")
    val reviewScoresCommunication: Int? = null,
    @SerialName("review_scores_location")
    val reviewScoresLocation: Int? = null,
    @SerialName("review_scores_rating")
    val reviewScoresRating: Int? = null,
    @SerialName("review_scores_value")
    val reviewScoresValue: Int? = null,
    @SerialName("reviews_per_month")
    val reviewsPerMonth: Double? = null,
    @SerialName("room_type")
    val roomType: String,
    @SerialName("security_deposit")
    val securityDeposit: Int? = null,
    @SerialName("smart_location")
    val smartLocation: String,
    @SerialName("space")
    val space: String? = null,
    @SerialName("state")
    val state: String,
    @SerialName("street")
    val street: String,
    @SerialName("summary")
    val summary: String? = null,
    @SerialName("transit")
    val transit: String? = null,
    @SerialName("weekly_price")
    val weeklyPrice: Int? = null,
    @SerialName("zipcode")
    val zipcode: String? = null
)