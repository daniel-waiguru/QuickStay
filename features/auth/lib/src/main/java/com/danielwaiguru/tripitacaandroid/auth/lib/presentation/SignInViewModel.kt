package com.danielwaiguru.tripitacaandroid.auth.lib.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.tripitacaandroid.auth.lib.R
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
internal class SignInViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    @Named("IO") private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _viewState1: MutableStateFlow<SignInUIState> = MutableStateFlow(SignInUIState())
    val viewState1: StateFlow<SignInUIState> = _viewState1.asStateFlow()

    fun onGoogleSignInResult(result: Result<User>) = viewModelScope.launch(ioDispatcher) {
        _viewState1.update { currentState -> currentState.copy(isLoading = true) }
        result.onSuccess { user ->
            val result = userDataRepository.saveUser(user)
            if (result.isSuccess) {
                _viewState1.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        isLoggedIn = true
                    )
                }
            } else {
                _viewState1.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorId = R.string.login_failed
                    )
                }
            }
        }.onFailure {
            Timber.e(it)
            _viewState1.update { currentState ->
                currentState.copy(
                    isLoading = false,
                    errorId = R.string.login_failed
                )
            }
        }
    }

    fun onEmailChange(email: String) {
        _viewState1.update { currentState ->
            currentState.copy(email = email)
        }
    }

    fun onPasswordChange(password: String) {
        _viewState1.update { currentState ->
            currentState.copy(password = password)
        }
    }

    fun onErrorShown() {
        _viewState1.update { currentState ->
            currentState.copy(errorId = null)
        }
    }
}