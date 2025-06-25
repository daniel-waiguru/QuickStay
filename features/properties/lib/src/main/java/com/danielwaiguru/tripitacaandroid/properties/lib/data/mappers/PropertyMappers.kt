package com.danielwaiguru.tripitacaandroid.properties.lib.data.mappers

import com.danielwaiguru.properties.contract.models.Property
import com.danielwaiguru.tripitacaandroid.properties.lib.data.models.dtos.PropertyDto
import com.danielwaiguru.tripitacaandroid.properties.lib.domain.utils.DateUtils
import java.util.Locale

internal fun PropertyDto.toProperty(): Property = Property(
    id = id,
    location = geolocation,
    hostName = hostName,
    hostPictureUrl = hostPictureUrl,
    name = name,
    photos = photos,
    price = price,
    propertyType = propertyType,
    roomType = roomType,
    reviewScoresRating = ((reviewScoresRating?.times(5))?.div(100))?.toDouble() ?: 0.0,
    numberOfReviews = numberOfReviews,
    reviewsPerMonth = reviewsPerMonth,
    city = city,
    country = country,
    amenities = amenities?.filterNot { it.contains("translation missing") } ?: emptyList(),
    cancellationPolicy = cancellationPolicy.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(
            Locale.ROOT
        ) else it.toString()
    },
    description = description,
    hostSince = DateUtils.parseJsonDate(hostSince),
    bookedDates = bookedDates,
    beds = beds,
    bathrooms = bathrooms,
    guestsIncluded = guestsIncluded,
    hostAbout = hostAbout,
    houseRules = houseRules,
    street = street
)
