package com.danielwaiguru.tripitacaandroid.properties.data.models.dtos


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
@Keep
data class PropertyDto(
    @SerializedName("access")
    val access: String,
    @SerializedName("accommodates")
    val accommodates: Int,
    @SerializedName("amenities")
    val amenities: List<String>?,
    @SerializedName("bathrooms")
    val bathrooms: Int,
    @SerializedName("bed_type")
    val bedType: String,
    @SerializedName("bedrooms")
    val bedrooms: Int,
    @SerializedName("beds")
    val beds: Int,
    @SerializedName("booked_dates")
    val bookedDates: List<String>,
    @SerializedName("calculated_host_listings_count")
    val calculatedHostListingsCount: Int,
    @SerializedName("calendar_updated")
    val calendarUpdated: String,
    @SerializedName("cancellation_policy")
    val cancellationPolicy: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("experiences_offered")
    val experiencesOffered: String,
    @SerializedName("extra_people")
    val extraPeople: Int,
    @SerializedName("first_review")
    val firstReview: String,
    @SerializedName("geolocation")
    val geolocation: GeolocationDto,
    @SerializedName("guests_included")
    val guestsIncluded: Int,
    @SerializedName("host_about")
    val hostAbout: String?,
    @SerializedName("host_acceptance_rate")
    val hostAcceptanceRate: Int?,
    @SerializedName("host_id")
    val hostId: String,
    @SerializedName("host_listings_count")
    val hostListingsCount: Int,
    @SerializedName("host_location")
    val hostLocation: String,
    @SerializedName("host_name")
    val hostName: String,
    @SerializedName("host_neighbourhood")
    val hostNeighbourhood: String,
    @SerializedName("host_picture_url")
    val hostPictureUrl: String,
    @SerializedName("host_response_rate")
    val hostResponseRate: Int,
    @SerializedName("host_response_time")
    val hostResponseTime: String,
    @SerializedName("host_since")
    val hostSince: String,
    @SerializedName("host_thumbnail_url")
    val hostThumbnailUrl: String,
    @SerializedName("host_total_listings_count")
    val hostTotalListingsCount: Int,
    @SerializedName("host_verifications")
    val hostVerifications: List<String>,
    @SerializedName("house_rules")
    val houseRules: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_review")
    val lastReview: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("market")
    val market: String,
    @SerializedName("maximum_nights")
    val maximumNights: Int,
    @SerializedName("minimum_nights")
    val minimumNights: Int,
    @SerializedName("monthly_price")
    val monthlyPrice: Int?,
    @SerializedName("name")
    val name: String,
    @SerializedName("neighborhood_overview")
    val neighborhoodOverview: String,
    @SerializedName("neighbourhood")
    val neighbourhood: String,
    @SerializedName("neighbourhood_cleansed")
    val neighbourhoodCleansed: String,
    @SerializedName("neighbourhood_group_cleansed")
    val neighbourhoodGroupCleansed: String,
    @SerializedName("notes")
    val notes: String,
    @SerializedName("number_of_reviews")
    val numberOfReviews: Int,
    @SerializedName("photos")
    val photos: List<String>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("property_type")
    val propertyType: String,
    @SerializedName("review_scores_accuracy")
    val reviewScoresAccuracy: Int,
    @SerializedName("review_scores_checkin")
    val reviewScoresCheckin: Int,
    @SerializedName("review_scores_cleanliness")
    val reviewScoresCleanliness: Int,
    @SerializedName("review_scores_communication")
    val reviewScoresCommunication: Int,
    @SerializedName("review_scores_location")
    val reviewScoresLocation: Int,
    @SerializedName("review_scores_rating")
    val reviewScoresRating: Int,
    @SerializedName("review_scores_value")
    val reviewScoresValue: Int,
    @SerializedName("reviews_per_month")
    val reviewsPerMonth: Double,
    @SerializedName("room_type")
    val roomType: String,
    @SerializedName("security_deposit")
    val securityDeposit: Int?,
    @SerializedName("smart_location")
    val smartLocation: String,
    @SerializedName("space")
    val space: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("transit")
    val transit: String,
    @SerializedName("weekly_price")
    val weeklyPrice: Int,
    @SerializedName("zipcode")
    val zipcode: String
)