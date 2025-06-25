package com.danielwaiguru.tripitacaandroid.booking.lib.models

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import com.danielwaiguru.properties.contract.models.Property
import com.danielwaiguru.tripitacaandroid.booking.lib.domain.utils.DateUtils.nextDayDate
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
