package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertieslisting

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.danielwaiguru.auth.contract.GetUserUseCase
import com.danielwaiguru.auth.contract.models.User
import com.danielwaiguru.testing.MainDispatcherRule
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.fakes.FakePropertiesRepository
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.fakes.property
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PropertiesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakePropertiesRepository()

    private fun viewModel(
        user: User? = null,
    ): PropertiesViewModel = PropertiesViewModel(
        propertiesRepository = repository,
        getUserUseCase = GetUserUseCase { flowOf(user) },
        savedStateHandle = SavedStateHandle(),
    )

    @Test
    fun `successful load exposes the properties`() = runTest {
        repository.listingResult = Result.success(listOf(property(id = "1"), property(id = "2")))

        viewModel().viewState.test {
            var state = awaitItem()
            while (state.properties.isEmpty()) state = awaitItem()

            assertThat(state.isLoading).isFalse()
            assertThat(state.errorMessage).isNull()
            assertThat(state.properties).hasSize(2)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `failed load exposes an error message`() = runTest {
        repository.listingResult = Result.failure(RuntimeException("network down"))

        viewModel().viewState.test {
            var state = awaitItem()
            while (state.errorMessage == null) state = awaitItem()

            assertThat(state.isLoading).isFalse()
            assertThat(state.errorMessage).isEqualTo("network down")
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `addToFavourite toggles the favourite flag`() = runTest {
        repository.listingResult = Result.success(listOf(property(id = "1"), property(id = "2")))
        val vm = viewModel()

        vm.viewState.test {
            var state = awaitItem()
            while (state.properties.isEmpty()) state = awaitItem()

            vm.addToFavourite("1")

            state = awaitItem()
            assertThat(state.properties.first { it.id == "1" }.isFavourite).isTrue()
            assertThat(state.properties.first { it.id == "2" }.isFavourite).isFalse()
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `selecting a filter narrows the filtered properties`() = runTest {
        repository.listingResult = Result.success(
            listOf(
                property(id = "1", amenities = listOf("TV")),
                property(id = "2", amenities = listOf("Wifi")),
            ),
        )
        val vm = viewModel()

        vm.viewState.test {
            var state = awaitItem()
            while (state.properties.isEmpty()) state = awaitItem()

            vm.onFilter("TV")

            state = awaitItem()
            while (state.selectedAmenityFilter != "TV") state = awaitItem()
            assertThat(state.filteredProperties.map { it.id }).containsExactly("1")
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `user details are mapped into the state`() = runTest {
        repository.listingResult = Result.success(listOf(property(id = "1")))
        val user = User(id = "u1", displayName = "Ada", email = "ada@x.com", photoUrl = "pic")

        viewModel(user = user).viewState.test {
            var state = awaitItem()
            while (state.username == "Anonymous") state = awaitItem()

            assertThat(state.username).isEqualTo("Ada")
            assertThat(state.userProfileUri).isEqualTo("pic")
            cancelAndConsumeRemainingEvents()
        }
    }
}