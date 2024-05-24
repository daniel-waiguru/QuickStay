package com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.danielwaiguru.tripitacaandroid.properties.data.repositories.PropertiesRepository
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.navigation.PropertyInfoScreen
import com.danielwaiguru.tripitacaandroid.shared.models.Property
import com.danielwaiguru.tripitacaandroid.shared.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyInfoViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val propertyArg = savedStateHandle.toRoute<PropertyInfoScreen>()
    private val _viewState: MutableStateFlow<ViewState<Property>> = MutableStateFlow(
        ViewState.Loading
    )
    val viewState: StateFlow<ViewState<Property>> = _viewState.asStateFlow()
    init {
        getProperty(propertyArg.propertyId)
    }

    private fun getProperty(propertyId: String) {
        viewModelScope.launch {
            val result = propertiesRepository.getFindPropertyById(propertyId)
            if (result.isSuccess) {
                _viewState.emit(
                    ViewState.Success(result.getOrNull()!!)
                )

            } else if (result.isFailure) {
                _viewState.emit(
                    ViewState.Error(message = result.exceptionOrNull()?.message)
                )
            }
        }
    }

    fun onAddToFavourite() {
        if (viewState.value is ViewState.Success) {
            viewModelScope.launch {
                val property = (viewState.value as ViewState.Success<Property>)
                    .data
                _viewState.emit(
                    ViewState.Success(
                        property.copy(isFavourite = property.isFavourite.not())
                    )
                )
            }
        }
    }
}