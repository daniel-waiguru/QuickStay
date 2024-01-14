package com.danielwaiguru.tripitacaandroid.booking.presentation.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.danielwaiguru.tripitacaandroid.shared.dateutils.DateUtils.nextDayDate
import com.danielwaiguru.tripitacaandroid.shared.models.Property
import java.util.Date
@Stable
data class BookingUIState(
    val property: Property? = null,
    val errorMessage: String? = null,
    val numberOfAdults: Int = 1,
    val numberOfChildren: Int = 0,
    val numberOfInfants: Int = 0,
    val checkInDate: Long = Date().time,
    val checkOutDate: Long = nextDayDate,
    @StringRes val errorId: Int? = null
)
