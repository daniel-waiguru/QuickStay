package com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.tripicaandroid.designsystem.components.IconText
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacAsyncImage
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaChip
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaPrimaryButton
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaRatingBar
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaTopAppBar
import com.danielwaiguru.tripicaandroid.designsystem.utils.Dimensions
import com.danielwaiguru.tripitacaandroid.properties.presentation.R
import com.danielwaiguru.tripitacaandroid.properties.presentation.components.TripitacaCalendar
import com.danielwaiguru.tripitacaandroid.properties.presentation.components.TripitacaGoogleMap
import com.danielwaiguru.tripitacaandroid.shared.models.Property
import com.danielwaiguru.tripitacaandroid.shared.state.ViewState
import com.google.android.gms.maps.model.LatLng
import com.danielwaiguru.tripitacaandroid.shared.R as SharedRes


@Composable
fun PropertyInfoRoute(
    onNavigateBack: () -> Unit,
    onClickBookNow: (id: String) -> Unit,
    viewModel: PropertyInfoViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    PropertyInfoScreen(
        state = uiState,
        modifier = Modifier
            .fillMaxSize(),
        onNavigateBack = onNavigateBack,
        onAddToFavourite = viewModel::onAddToFavourite,
        onClickBookNow = onClickBookNow
    )
}

@Composable
fun PropertyInfoScreen(
    onNavigateBack: () -> Unit,
    onAddToFavourite: () -> Unit,
    onClickBookNow: (id: String) -> Unit,
    state: ViewState<Property>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        when (state) {
            is ViewState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = state.message ?: stringResource(
                            id = R.string.something_went_wrong
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }

            ViewState.Loading -> {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                ) {
//                    ProgressIndicator(
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                    )
//                }
            }
            is ViewState.Success -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(-(15).dp)
                ) {
                    LazyColumn(
                        Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(-(15).dp)
                    ) {
                        item {
                            PropertyImagesSlider(
                                property = state.data,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .height(300.dp),
                                onAddToFavourite = onAddToFavourite,
                                onNavigateBack = onNavigateBack
                            )
                        }
                        item {
                            PropertyInfoSection(
                                property = state.data,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight()
                                    .background(
                                        MaterialTheme.colorScheme.background,
                                        shape = RoundedCornerShape(
                                            topStart = 20.dp,
                                            topEnd = 20.dp
                                        )
                                    )
                                    .padding(Dimensions.ScreenPadding)
                            )
                        }
                    }
                    TripitacaPrimaryButton(
                        text = stringResource(id = R.string.book_now),
                        onClick = {
                            onClickBookNow(state.data.id)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Dimensions.ScreenPadding,
                                vertical = Dimensions.ScreenPadding
                            )
                    )
                }
            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun PropertyImagesSlider(
    property: Property,
    onNavigateBack: () -> Unit,
    onAddToFavourite: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { property.photos.size }
    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            state = pagerState
        ) { index ->
            TripitacAsyncImage(
                url = property.photos[index],
                contentDescription = stringResource(R.string.property, index),
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }
        Row(
            modifier = Modifier
                .offset(y = -(20).dp)
                .fillMaxWidth(0.5f)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement
                .Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(property.photos.size) { index ->
                Box(
                    modifier = Modifier
                        .size(
                            if (index == pagerState.currentPage) {
                                12.dp
                            } else {
                                10.dp
                            }
                        )
                        .clip(RoundedCornerShape(100))
                        .background(
                            if (index == pagerState.currentPage) {
                                MaterialTheme.colorScheme.primary
                            } else {
                                MaterialTheme.colorScheme.surface
                            }
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        TripitacaTopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(horizontal = Dimensions.ScreenPadding),
            navigationIcon = {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.outlineVariant.copy(
                                alpha = 0.4f
                            ),
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.nav_icon_content_desc
                        )
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = onAddToFavourite,
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.outlineVariant.copy(
                                alpha = 0.4f
                            ),
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    Icon(
                        imageVector = if (property.isFavourite) {
                            Icons.Rounded.Favorite
                        } else {
                            Icons.Rounded.FavoriteBorder
                        },
                        contentDescription = stringResource(R.string.add_to_favourite),
                        tint = if (property.isFavourite) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            Color.Unspecified
                        }
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )
    }
}

@Composable
fun PropertyInfoSection(
    property: Property,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement
            .spacedBy(6.dp)
    ) {
        Text(
            text = property.name,
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold
        )
        IconText(
            text = property.street,
            icon = Icons.Outlined.LocationOn,
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = -(5).dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TripitacaRatingBar(
                modifier = Modifier
                    .height(30.dp)
                    .wrapContentWidth(),
                rating = property.reviewScoresRating
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${property.reviewScoresRating}(${property.numberOfReviews})",
                modifier = Modifier
                    .weight(1f)
            )
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
                    append(" / night")
                },
                textAlign = TextAlign.End,
                fontSize = 20.sp
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(
                    8.dp
                )
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = pluralStringResource(
                    R.plurals.guests_placeholder,
                    property.guestsIncluded,
                    property.guestsIncluded
                ),
                textAlign = TextAlign.Center,
            )
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10)),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Text(
                text = pluralStringResource(
                    R.plurals.beds_placeholder,
                    property.beds,
                    property.beds
                ),
                textAlign = TextAlign.Center,
            )
            VerticalDivider(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10)),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Text(
                text = pluralStringResource(
                    R.plurals.baths_placeholder,
                    property.bathrooms,
                    property.bathrooms
                ),
                textAlign = TextAlign.Center,
            )
        }

        Text(
            text = stringResource(R.string.amenities_available),
            fontWeight = FontWeight.SemiBold
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState())
            ) {
                property.amenities.forEach { amenity ->
                    TripitacaChip(
                        label = amenity,
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 4.dp)
                    )
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        Text(
            text = stringResource(R.string.availability),
            fontWeight = FontWeight.SemiBold
        )
        TripitacaCalendar(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(MaterialTheme.shapes.medium),
            excludes = property.bookedDates
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(bottom = 5.dp)
        )
        Text(
            text = stringResource(R.string.about),
            fontWeight = FontWeight.SemiBold,
        )
        Text(
            text = property.description,
            modifier = Modifier
                .fillMaxWidth()
        )
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        Text(
            text = stringResource(R.string.cancelation_policy),
            fontWeight = FontWeight.SemiBold
        )
        Text(text = property.cancellationPolicy)
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        if (property.houseRules.isNullOrBlank().not()) {
            Text(
                text = stringResource(R.string.house_rules),
                fontWeight = FontWeight.SemiBold
            )
            Text(text = property.houseRules!!)
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 5.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.hosted_by, property.hostName),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.joined_in, property.hostSince),
                    fontSize = 12.sp
                )
            }
            TripitacAsyncImage(
                url = property.hostPictureUrl,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape),
                contentDescription = property.hostName,
                contentScale = ContentScale.FillBounds
            )
        }
        if (property.hostAbout != null) {
            Text(
                text = property.hostAbout!!,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 5.dp)
        )
        Text(
            text = stringResource(R.string.where_you_will_be),
            fontWeight = FontWeight.SemiBold
        )
        TripitacaGoogleMap(
            location = LatLng(property.location.latitude, property.location.longitude),
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 2800.dp, max = 230.dp)
                .clip(MaterialTheme.shapes.medium),
            title = property.name
        )
    }
}
