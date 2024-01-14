package com.danielwaiguru.tripitacaandroid.properties.data.mappers

import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.GeolocationDto
import com.danielwaiguru.tripitacaandroid.properties.data.models.dtos.PropertyDto
import com.danielwaiguru.tripitacaandroid.shared.dateutils.DateUtils
import com.danielwaiguru.tripitacaandroid.shared.models.Property

fun PropertyDto.toProperty(): Property = Property(
    id = id,
    location = geolocation.toGeoLocation(),
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
    cancellationPolicy = cancellationPolicy,
    description = description,
    hostSince = DateUtils.parseJsonDate(hostSince),
    bookedDates = bookedDates,
    beds = beds,
    bathrooms = bathrooms,
    guestsIncluded = guestsIncluded,
    hostAbout = hostAbout
)

fun GeolocationDto.toGeoLocation(): Property.GeoLocation = Property.GeoLocation(
    latitude = lat,
    longitude = lon
)