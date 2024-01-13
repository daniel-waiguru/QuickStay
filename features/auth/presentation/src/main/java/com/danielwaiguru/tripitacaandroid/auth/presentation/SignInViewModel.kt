package com.danielwaiguru.tripitacaandroid.auth.presentation

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielwaiguru.tripitacaandroid.auth.data.repositories.UserDataRepository
import com.danielwaiguru.tripitacaandroid.auth.presentation.utils.GoogleSignInWrapper
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.Dispatcher
import com.danielwaiguru.tripitacaandroid.shared.dispatchers.DispatcherProvider
import com.danielwaiguru.tripitacaandroid.shared.state.ViewState
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val googleSignInWrapper: GoogleSignInWrapper,
    private val userDataRepository: UserDataRepository,
    @Dispatcher(DispatcherProvider.IO) private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _viewState: MutableStateFlow<ViewState<String>?> = MutableStateFlow(null)
    val viewState: StateFlow<ViewState<String>?> = _viewState.asStateFlow()

    fun onUserSignIn(data: Intent?) = viewModelScope.launch(ioDispatcher) {
        _viewState.emit(ViewState.Loading)
        try {
            val task = googleSignInWrapper.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)
            val username = account.displayName ?: account.familyName ?: account.email ?: ""
            val result = userDataRepository.saveUser(username)
            if (result.isSuccess) {
                _viewState.emit(ViewState.Success(username))
            } else {
                _viewState.emit(ViewState.Error(messageRes = R.string.login_failed))
            }
        } catch (e: Exception) {
            Timber.e(e)
            _viewState.emit(ViewState.Error(messageRes = R.string.login_failed))
        }
    }
}