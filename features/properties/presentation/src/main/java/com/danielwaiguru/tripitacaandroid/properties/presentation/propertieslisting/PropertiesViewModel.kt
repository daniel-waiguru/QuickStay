package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.auth.contract.GetUserUseCase
import com.danielwaiguru.tripitacaandroid.properties.data.repositories.PropertiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository,
    getUserUseCase: GetUserUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val filter = savedStateHandle.getStateFlow<String?>(FILTER_KEY, null)

    private val _viewState: MutableStateFlow<PropertiesUIState> = MutableStateFlow(
        PropertiesUIState()
    )
    val viewState: StateFlow<PropertiesUIState> = combine(
        _viewState.asStateFlow(),
        filter,
        getUserUseCase.getUser()
    ) { state, filter, user ->
        state.copy(
            selectedAmenityFilter = if (
                filter == state.selectedAmenityFilter &&
                state.selectedAmenityFilter != null
            ) {
                null
            } else {
                filter
            },
            filteredProperties = if (filter != null) {
                state.properties.filter {
                    it.amenities.contains(filter)
                }
            } else {
                state.properties
            },
            username = if (user?.displayName.isNullOrBlank().not()) {
                user?.displayName ?: "Anonymous"
            } else {
                "Anonymous"
            },
            userProfileUri = user?.photoUrl
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        _viewState.value
    )

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

    fun onFilter(filter: String?) {
        /**
         * Persist against process death
         */
        savedStateHandle[FILTER_KEY] = filter
    }

    companion object {
        const val FILTER_KEY = "com.danielwaiguru.tripitacaandroid.properties.FilterKey"
    }
}