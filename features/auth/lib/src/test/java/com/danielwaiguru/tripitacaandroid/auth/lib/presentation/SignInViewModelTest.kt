package com.danielwaiguru.tripitacaandroid.auth.lib.presentation

import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.testing.MainDispatcherRule
import com.danielwaiguru.tripitacaandroid.auth.lib.R
import com.danielwaiguru.tripitacaandroid.auth.lib.presentation.fakes.FakeUserDataRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: FakeUserDataRepository
    private lateinit var viewModel: SignInViewModel

    private val user = User(
        id = "1",
        displayName = "Daniel",
        email = "daniel@example.com",
        photoUrl = null,
    )

    @Before
    fun setUp() {
        repository = FakeUserDataRepository()
        viewModel = SignInViewModel(repository, mainDispatcherRule.testDispatcher)
    }

    @Test
    fun `successful sign-in saves the user and marks logged in`() = runTest {
        viewModel.onGoogleSignInResult(Result.success(user))

        val state = viewModel.viewState1.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.isLoggedIn).isTrue()
        assertThat(state.errorId).isNull()
        assertThat(repository.savedUser).isEqualTo(user)
    }

    @Test
    fun `sign-in success but save failure surfaces login error`() = runTest {
        repository.saveResult = Result.failure(IllegalStateException("boom"))

        viewModel.onGoogleSignInResult(Result.success(user))

        val state = viewModel.viewState1.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.isLoggedIn).isFalse()
        assertThat(state.errorId).isEqualTo(R.string.login_failed)
    }

    @Test
    fun `failed sign-in result surfaces login error`() = runTest {
        viewModel.onGoogleSignInResult(Result.failure(RuntimeException("cancelled")))

        val state = viewModel.viewState1.value
        assertThat(state.isLoading).isFalse()
        assertThat(state.isLoggedIn).isFalse()
        assertThat(state.errorId).isEqualTo(R.string.login_failed)
    }

    @Test
    fun `onEmailChange updates the email`() {
        viewModel.onEmailChange("new@example.com")

        assertThat(viewModel.viewState1.value.email).isEqualTo("new@example.com")
    }

    @Test
    fun `onPasswordChange updates the password`() {
        viewModel.onPasswordChange("s3cret")

        assertThat(viewModel.viewState1.value.password).isEqualTo("s3cret")
    }

    @Test
    fun `onErrorShown clears the error`() = runTest {
        viewModel.onGoogleSignInResult(Result.failure(RuntimeException("cancelled")))
        assertThat(viewModel.viewState1.value.errorId).isNotNull()

        viewModel.onErrorShown()

        assertThat(viewModel.viewState1.value.errorId).isNull()
    }
}