package com.danielwaiguru.tripicaandroid.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarHalf
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.ceil
import kotlin.math.floor


@Composable
fun TripitacaRatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    maxSize: Int = 5,
    displayEmptyStarts: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (maxSize - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = color
            )
        }
        if (halfStar) {
            Icon(
                imageVector = Icons.Outlined.StarHalf,
                contentDescription = null,
                tint = color
            )
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.StarOutline,
                contentDescription = null,
                tint = color
            )
        }
    }
}