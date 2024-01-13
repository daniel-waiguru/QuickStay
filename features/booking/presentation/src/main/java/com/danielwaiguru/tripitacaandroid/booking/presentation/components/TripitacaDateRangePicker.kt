package com.danielwaiguru.tripitacaandroid.booking.presentation.components

import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun TripitacaDateRangePicker(
    modifier: Modifier = Modifier,
    initialSelectedStartDateMillis: Long? = null,
    initialSelectedEndDateMillis: Long? = null,
    onDateChanged: (startDate: Long, endDate: Long) -> Unit,
    excludes: List<String>
) {
    DateRangePickerComponent(
        onDateChanged = onDateChanged,
        excludes = excludes,
        modifier = modifier,
        initialSelectedEndDateMillis = initialSelectedEndDateMillis,
        initialSelectedStartDateMillis = initialSelectedStartDateMillis
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerComponent(
    modifier: Modifier = Modifier,
    onDateChanged: (startDate: Long, endDate: Long) -> Unit,
    initialSelectedStartDateMillis: Long? = null,
    initialSelectedEndDateMillis: Long? = null,
    excludes: List<String>
) {

    val state = rememberDateRangePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val localDate = Instant.ofEpochMilli(utcTimeMillis).atZone(ZoneId.of("UTC"))
                    .toLocalDate()

                val excludesDates = excludes.map {
                    LocalDate.parse(
                        it,
                        DateTimeFormatter.ofPattern("MM/dd/yyyy", Locale.getDefault())
                    )
                }
                return excludesDates.contains(localDate).not()
            }

        },
        initialSelectedStartDateMillis = initialSelectedStartDateMillis,
        initialSelectedEndDateMillis = initialSelectedEndDateMillis
    )
    LaunchedEffect(key1 = state.selectedStartDateMillis, key2 = state.selectedEndDateMillis) {
        val startDate = state.selectedStartDateMillis ?: return@LaunchedEffect
        val endDate = state.selectedEndDateMillis ?: return@LaunchedEffect
        onDateChanged(startDate, endDate)
    }
    DateRangePicker(
        state = state,
        modifier = modifier,
        showModeToggle = false,
        title = null,
        headline = null
    )
}