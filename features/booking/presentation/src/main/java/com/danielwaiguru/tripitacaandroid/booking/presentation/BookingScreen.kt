package com.danielwaiguru.tripitacaandroid.booking.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Remove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaPrimaryButton
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaRoundedInputField
import com.danielwaiguru.tripicaandroid.designsystem.components.TripitacaTopAppBar
import com.danielwaiguru.tripicaandroid.designsystem.previews.DevicePreviews
import com.danielwaiguru.tripicaandroid.designsystem.theme.TripitacaAndroidTheme
import com.danielwaiguru.tripicaandroid.designsystem.utils.Dimensions
import com.danielwaiguru.tripitacaandroid.booking.presentation.components.TripitacaDateRangePicker
import com.danielwaiguru.tripitacaandroid.booking.presentation.models.BookingEvent
import com.danielwaiguru.tripitacaandroid.booking.presentation.models.BookingUIState
import com.danielwaiguru.tripitacaandroid.booking.presentation.models.CountAction
import com.danielwaiguru.tripitacaandroid.shared.dateutils.DateUtils.formatDate
import kotlinx.coroutines.launch

@Composable
fun BookingScreenRoute(
    viewModel: BookingViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.viewState.collectAsStateWithLifecycle()
    BookingScreen(
        state = uiState,
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.ScreenPadding),
        onEvent = viewModel::onEvent,
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingScreen(
    onEvent: (BookingEvent) -> Unit,
    onNavigateBack: () -> Unit,
    state: BookingUIState,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    LaunchedEffect(key1 = state.errorMessage) {
        val error = state.errorMessage
        if (error.isNullOrBlank().not()) {
            snackbarHostState.showSnackbar(error!!)
        }
    }
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TripitacaTopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
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
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 2.dp)
                .padding(top = 2.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement
                .spacedBy(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.outlineVariant.copy(
                            alpha = 0.1f
                        ), MaterialTheme.shapes.medium
                    )
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (state.property != null) {
                    TripitacaDateRangePicker(
                        excludes = state.property.bookedDates,
                        onDateChanged = { startDate, endDate ->
                            onEvent(BookingEvent.OnDateChanged(startDate, endDate))
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(350.dp),
                        initialSelectedStartDateMillis = state.checkInDate,
                        initialSelectedEndDateMillis = state.checkOutDate
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    DateInput(
                        label = stringResource(id = R.string.check_in),
                        value = formatDate(state.checkInDate),
                        modifier = Modifier
                            .weight(1f)
                    )
                    DateInput(
                        label = stringResource(id = R.string.check_out),
                        value = formatDate(state.checkOutDate),
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .background(
                        MaterialTheme.colorScheme.outlineVariant.copy(
                            alpha = 0.1f
                        ), MaterialTheme.shapes.medium
                    )
                    .padding(10.dp),
            ) {
                Text(
                    text = stringResource(R.string.who_s_coming),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontSize = 18.sp
                )
                PeopleCountItem(
                    title = stringResource(R.string.adults),
                    description = stringResource(R.string.ages_13_or_above),
                    value = state.numberOfAdults,
                    onAction = {
                        onEvent(
                            when (it) {
                                CountAction.Add -> BookingEvent.AddAdult
                                CountAction.SUBTRACT -> BookingEvent.SubtractAdult
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                PeopleCountItem(
                    title = stringResource(R.string.children),
                    description = stringResource(R.string.ages_2_12),
                    value = state.numberOfChildren,
                    onAction = {
                        onEvent(
                            when (it) {
                                CountAction.Add -> BookingEvent.AddChild
                                CountAction.SUBTRACT -> BookingEvent.SubtractChild
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                PeopleCountItem(
                    title = stringResource(R.string.infants),
                    description = stringResource(R.string.under_2),
                    value = state.numberOfInfants,
                    onAction = {
                        onEvent(
                            when (it) {
                                CountAction.Add -> BookingEvent.AddInfant
                                CountAction.SUBTRACT -> BookingEvent.SubtractInfant
                            }
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            TripitacaPrimaryButton(
                text = stringResource(R.string.continue_text),
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Successfully booked")
                        onNavigateBack()
                    }
                }
            )
        }
    }
}


@Composable
private fun DateInput(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        TripitacaRoundedInputField(
            value = value,
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = true
        )
    }
}

@Composable
private fun PeopleCountItem(
    title: String,
    description: String,
    value: Int,
    onAction: (CountAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = description,
                fontSize = 12.sp
            )
        }
        CountComponent(
            value = value,
            onAction = onAction
        )
    }
}

@Composable
fun CountComponent(
    value: Int,
    onAction: (CountAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(100)
                )
                .size(25.dp),
            onClick = { onAction(CountAction.SUBTRACT) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Remove,
                contentDescription = stringResource(R.string.reduce_count),
                modifier = Modifier
                    .size(15.dp)
            )
        }
        Text(
            text = value.toString(),
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 10.dp)

        )
        IconButton(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = RoundedCornerShape(100)
                )
                .size(25.dp),
            onClick = { onAction(CountAction.Add) }
        ) {
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = stringResource(R.string.reduce_count),
                modifier = Modifier
                    .size(15.dp)
            )
        }
    }
}

@DevicePreviews
@Composable
fun PeopleCountItemPreview() {
    TripitacaAndroidTheme {
        PeopleCountItem(
            value = 1,
            modifier = Modifier.fillMaxWidth(),
            onAction = {},
            title = "Checkout",
            description = "Age 13 or above"
        )
    }
}

@DevicePreviews
@Composable
fun CountComponentPreview() {
    TripitacaAndroidTheme {
        CountComponent(
            value = 1,
            modifier = Modifier.fillMaxWidth(),
            onAction = {}
        )
    }
}
