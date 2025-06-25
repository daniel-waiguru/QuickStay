package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.components

import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun QuickStayCalendar(
    modifier: Modifier = Modifier,
    excludes: List<String> = emptyList()
) {
    CalendarViewPicker(
        modifier = modifier,
        excludes = excludes
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CalendarViewPicker(
    modifier: Modifier = Modifier,
    excludes: List<String>
) {
    val datePickerState = rememberDatePickerState(
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

        }
    )
    DatePicker(
        state = datePickerState,
        modifier = modifier,
        showModeToggle = false,
        headline = null,
        title = null
    )
}