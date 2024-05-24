package com.danielwaiguru.tripitacaandroid.booking.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.danielwaiguru.tripitacaandroid.booking.presentation.models.BookingEvent
import com.danielwaiguru.tripitacaandroid.booking.presentation.models.BookingUIState
import com.danielwaiguru.tripitacaandroid.booking.presentation.navigation.BookingScreen
import com.danielwaiguru.tripitacaandroid.properties.data.repositories.PropertiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookingViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _viewState: MutableStateFlow<BookingUIState> = MutableStateFlow(
        BookingUIState()
    )
    val viewState: StateFlow<BookingUIState> = _viewState.asStateFlow()
    private val propertyArg = savedStateHandle.toRoute<BookingScreen>()

    init {
        getProperty(propertyArg.propertyId)
    }

    private fun getProperty(propertyId: String) {
        viewModelScope.launch {
            val result = propertiesRepository.getFindPropertyById(propertyId)
            if (result.isSuccess) {
                _viewState.update { currentState ->
                    currentState.copy(property = result.getOrNull())
                }

            } else if (result.isFailure) {
                _viewState.update { currentState ->
                    currentState.copy(errorMessage = result.exceptionOrNull()?.message)
                }
            }
        }
    }

    fun onEvent(bookingEvent: BookingEvent) {
        _viewState.update { currentState ->
            when(bookingEvent) {
                BookingEvent.AddAdult -> {
                    currentState.copy(
                        numberOfAdults = currentState.numberOfAdults.plus(1)
                    )
                }
                BookingEvent.AddChild -> {
                    currentState.copy(
                        numberOfChildren = currentState.numberOfChildren.plus(1)
                    )
                }
                BookingEvent.AddInfant -> {
                    currentState.copy(
                        numberOfInfants = currentState.numberOfInfants.plus(1)
                    )
                }
                BookingEvent.SubtractAdult -> {
                    if (currentState.numberOfAdults <= 1) return@update currentState
                    currentState.copy(
                        numberOfAdults = currentState.numberOfAdults.minus(1)
                    )
                }
                BookingEvent.SubtractChild -> {
                    if (currentState.numberOfChildren <= 0) return@update currentState
                    currentState.copy(
                        numberOfChildren = currentState.numberOfChildren.minus(1)
                    )
                }
                BookingEvent.SubtractInfant -> {
                    if (currentState.numberOfInfants <= 0) return@update currentState
                    currentState.copy(
                        numberOfInfants = currentState.numberOfInfants.minus(1)
                    )
                }
                is BookingEvent.OnDateChanged -> {
                    currentState.copy(
                        checkInDate = bookingEvent.startDate,
                        checkOutDate = bookingEvent.endDate
                    )
                }
            }
        }
    }
}