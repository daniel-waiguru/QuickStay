package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertyinfo

import androidx.lifecycle.SavedStateHandle
import com.danielwaiguru.testing.MainDispatcherRule
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.fakes.FakePropertiesRepository
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.fakes.property
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.state.ViewState
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class PropertyInfoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repository = FakePropertiesRepository()

    private fun viewModel(propertyId: String = "1"): PropertyInfoViewModel =
        PropertyInfoViewModel(
            propertiesRepository = repository,
            savedStateHandle = SavedStateHandle(mapOf("propertyId" to propertyId)),
        )

    @Test
    fun `successful load exposes the property`() = runTest {
        repository.findResult = Result.success(property(id = "1", name = "Sunny Loft"))

        val state = viewModel().viewState.value

        assertThat(state).isInstanceOf(ViewState.Success::class.java)
        assertThat((state as ViewState.Success).data.name).isEqualTo("Sunny Loft")
    }

    @Test
    fun `failed load exposes an error`() = runTest {
        repository.findResult = Result.failure(RuntimeException("not found"))

        val state = viewModel().viewState.value

        assertThat(state).isInstanceOf(ViewState.Error::class.java)
        assertThat((state as ViewState.Error).message).isEqualTo("not found")
    }

    @Test
    fun `onAddToFavourite toggles the favourite flag`() = runTest {
        repository.findResult = Result.success(property(id = "1", isFavourite = false))
        val vm = viewModel()

        vm.onAddToFavourite()

        val state = vm.viewState.value as ViewState.Success
        assertThat(state.data.isFavourite).isTrue()
    }
}
