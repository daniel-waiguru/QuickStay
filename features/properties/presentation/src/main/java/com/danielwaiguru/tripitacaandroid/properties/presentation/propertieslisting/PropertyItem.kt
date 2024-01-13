package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacAsyncImage
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaRatingBar
import com.danielwaiguru.tripitacaandroid.shared.models.Property
import com.danielwaiguru.tripitacaandroid.shared.R as SharedRes


@Composable
internal fun PropertyItem(
    modifier: Modifier = Modifier,
    onFavourite: (id: String) -> Unit,
    property: Property
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                TripitacAsyncImage(
                    url = property.photos[0],
                    contentDescription = property.name,
                    modifier = Modifier
                        .matchParentSize(),

                    )
                IconButton(
                    onClick = {
                        onFavourite(property.id)
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = if (property.isFavourite) {
                            Icons.Rounded.Favorite
                        } else {
                            Icons.Rounded.FavoriteBorder
                        },
                        contentDescription = null,
                        tint = if (property.isFavourite) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surface
                        }
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 10.dp, top = 10.dp)
                ) {
                    Text(
                        text = property.propertyType,
                        modifier = Modifier
                            .background(
                                MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(100)
                            )
                            .padding(
                                horizontal = 8.dp,
                            )
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(6.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = property.name,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = "${property.city}, ${property.country}"
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    TripitacaRatingBar(
                        modifier = Modifier
                            .height(20.dp)
                            .wrapContentWidth(),
                        rating = property.reviewsPerMonth ?: 0.0
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${property.reviewsPerMonth ?: 0.0}(${property.numberOfReviews})")
                }
                Text(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.ExtraBold)) {
                            append(
                                stringResource(
                                    id = SharedRes.string.price_placeholder,
                                    property.price
                                )
                            )
                        }
                        append("/night")
                    },
                    textAlign = TextAlign.End
                )
            }

        }
    }
}


