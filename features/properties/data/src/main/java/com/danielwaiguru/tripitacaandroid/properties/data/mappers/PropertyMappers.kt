package com.danielwaiguru.tripitacaandroid.properties.data.mappers

import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.PropertyDto
import com.danielwaiguru.tripitacaandroid.shared.dateutils.DateUtils
import com.danielwaiguru.tripitacaandroid.shared.models.Property
import java.util.Locale

fun PropertyDto.toProperty(): Property = Property(
    id = id,
    location = geolocation,
    hostName = hostName,
    hostPictureUrl = hostPictureUrl,
    name = name,
    photos = photos,
    price = price,
    propertyType = propertyType,
    roomType = roomType,
    reviewScoresRating = ((reviewScoresRating * 5) / 100).toDouble(),
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
