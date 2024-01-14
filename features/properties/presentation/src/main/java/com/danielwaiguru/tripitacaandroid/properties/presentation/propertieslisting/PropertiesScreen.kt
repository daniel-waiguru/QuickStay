package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.tripicaandroid.designsystem.components.ShimmerProgressIndicator
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacAsyncImage
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaTopAppBar
import com.danielwaiguru.tripicaandroid.designsystem.utils.Dimensions
import com.danielwaiguru.tripitacaandroid.properties.presentation.R
import com.danielwaiguru.tripitacaandroid.shared.models.Property

@Composable
fun PropertiesRoute(
    onClick: (id: String) -> Unit,
    viewModel: PropertiesViewModel = hiltViewModel()
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    PropertiesScreen(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimensions.ScreenPadding),
        onClick = onClick,
        onFavourite = viewModel::addToFavourite,
        state = uiState,
        onFilter = viewModel::onFilter
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertiesScreen(
    modifier: Modifier = Modifier,
    onFavourite: (id: String) -> Unit,
    onClick: (id: String) -> Unit,
    onFilter: (String?) -> Unit,
    state: PropertiesUIState
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TripitacaTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    Text(
                        text = stringResource(id = R.string.hey, state.username),
                        fontSize = 16.sp
                    )
                },
                actions = {
                    TripitacAsyncImage(
                        url = state.userProfileUri ?: "".toUri(),
                        contentDescription = state.username,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.FillBounds
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            AnimatedVisibility(visible = state.selectedAmenityFilter.isNullOrBlank().not()) {
                SmallFloatingActionButton(
                    onClick = { onFilter(null) },
                ) {
                    Text(
                        text = stringResource(R.string.clear_filters),
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            when {
                state.errorMessage != null && state.isLoading.not() -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Text(
                            text = state.errorMessage,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                else -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        state.amenities.forEach { amenity ->
                            ElevatedFilterChip(
                                selected = state.selectedAmenityFilter == amenity,
                                onClick = { onFilter(amenity.trim())},
                                label = {
                                    Text(text = amenity)
                                },
                                leadingIcon = {
                                    if (state.selectedAmenityFilter == amenity) {
                                        Icon(
                                            imageVector = Icons.Rounded.Done,
                                            contentDescription = amenity
                                        )
                                    }
                                }
                            )
                        }
                    }
                    PropertiesSection(
                        properties = state.filteredProperties,
                        isLoading = state.isLoading,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 5.dp),
                        onClick = onClick,
                        onFavourite = onFavourite
                    )
                }
            }
        }
    }

}

@Composable
fun PropertiesSection(
    modifier: Modifier = Modifier,
    properties: List<Property>,
    onFavourite: (id: String) -> Unit,
    onClick: (id: String) -> Unit,
    isLoading: Boolean
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement
            .spacedBy(10.dp)
    ) {
        items(properties, key = { it.id }) { property ->
            val clickableModifier = remember(property) {
                Modifier
                    .clickable { onClick(property.id) }
                    .fillMaxWidth()
            }
            ShimmerProgressIndicator(
                isLoading = isLoading,
                contentAfterLoading = {
                    PropertyItem(
                        property = property,
                        modifier = clickableModifier,
                        onFavourite = onFavourite
                    )
                }
            )
        }
    }
}