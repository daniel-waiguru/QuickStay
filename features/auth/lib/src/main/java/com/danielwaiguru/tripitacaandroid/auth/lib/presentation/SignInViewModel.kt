package com.danielwaiguru.tripitacaandroid.auth.lib.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.tripitacaandroid.auth.lib.R
import com.danielwaiguru.tripitacaandroid.auth.lib.domain.repositories.UserDataRepository
import com.danielwaiguru.tripitacaandroid.auth.lib.presentation.utils.GoogleSignInWrapper
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.Dispatcher
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.DispatcherProvider
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class SignInViewModel @Inject constructor(
    private val googleSignInWrapper: GoogleSignInWrapper,
    private val userDataRepository: UserDataRepository,
    @Dispatcher(DispatcherProvider.IO) private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _viewState1: MutableStateFlow<SignInUIState> = MutableStateFlow(SignInUIState())
    val viewState1: StateFlow<SignInUIState> = _viewState1.asStateFlow()

    fun onUserSignIn(data: Intent?) = viewModelScope.launch(ioDispatcher) {
        _viewState1.update { currentState -> currentState.copy(isLoading = true) }
        try {
            val task = googleSignInWrapper.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            Timber.d("Tok: ${account.idToken}")
            val username = account.displayName ?: account.familyName ?: account.email ?: ""
            val user = User(
                id = account.id,
                displayName = username,
                email = account.email,
                photoUrl = account.photoUrl?.toString()
            )
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
        } catch (e: Exception) {
            Timber.e(e)
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