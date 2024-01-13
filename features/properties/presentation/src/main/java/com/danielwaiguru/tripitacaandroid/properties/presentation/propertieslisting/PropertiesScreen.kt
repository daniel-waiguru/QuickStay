package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.tripicaandroid.designsystem.components.ShimmerProgressIndicator
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaTopAppBar
import com.danielwaiguru.tripicaandroid.designsystem.utils.Dimensions
import com.danielwaiguru.tripitacaandroid.properties.data.models.Property
import com.danielwaiguru.tripitacaandroid.properties.presentation.R

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
        state = uiState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PropertiesScreen(
    modifier: Modifier = Modifier,
    onFavourite: (id: String) -> Unit,
    onClick: (id: String) -> Unit,
    state: PropertiesUIState
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TripitacaTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = stringResource(id = R.string.hey)
                        )
                        if (state.username.isNullOrBlank().not()) {
                            Text(
                                text = state.username!!,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            )
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
                    PropertiesSection(
                        properties = state.properties,
                        isLoading = state.isLoading,
                        modifier = Modifier
                            .fillMaxSize(),
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
                Modifier.clickable { onClick(property.id) }
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