package com.danielwaiguru.tripitacaandroid.booking.lib.models


enum class CountAction {
    Add, SUBTRACT
}
sealed interface BookingEvent {
    data object AddAdult: BookingEvent
    data object SubtractAdult: BookingEvent
    data object AddChild: BookingEvent
    data object SubtractChild: BookingEvent
    data object AddInfant: BookingEvent
    data object SubtractInfant: BookingEvent
    data class OnDateChanged(val startDate: Long, val endDate: Long): BookingEvent
}