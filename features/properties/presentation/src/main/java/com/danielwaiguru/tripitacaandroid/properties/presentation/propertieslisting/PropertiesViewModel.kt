package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.tripitacaandroid.properties.data.repositories.PropertiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository
): ViewModel() {
    private val _viewState: MutableStateFlow<PropertiesUIState> = MutableStateFlow(
        PropertiesUIState()
    )
    val viewState: StateFlow<PropertiesUIState> = _viewState.asStateFlow()
    init {
        getProperties()
    }

    private fun getProperties() {
        _viewState.update { currentState ->
            currentState.copy(isLoading = true, errorMessage = null)
        }
        viewModelScope.launch {
            val result = propertiesRepository.getListing()
            if (result.isSuccess) {
                _viewState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorMessage = null,
                        properties = result.getOrNull() ?: emptyList()
                    )
                }
            } else if (result.isFailure) {
                _viewState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorMessage = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }

    fun addToFavourite(id: String) {
        _viewState.update { currentState ->
            currentState.copy(
                properties = currentState.properties.map { property ->
                    if (property.id == id) {
                        property.copy(isFavourite = property.isFavourite.not())
                    } else {
                        property
                    }
                }
            )
        }
    }
}