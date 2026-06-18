package com.danielwaiguru.tripitacaandroid.booking.lib

import androidx.lifecycle.SavedStateHandle
import com.danielwaiguru.properties.contract.GetPropertyUseCase
import com.danielwaiguru.properties.contract.models.Property
import com.danielwaiguru.testing.MainDispatcherRule
import com.danielwaiguru.tripitacaandroid.booking.lib.models.BookingEvent
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BookingViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private fun viewModel(
        propertyId: String = "1",
        result: Result<Property> = Result.success(property(id = "1")),
    ): BookingViewModel = BookingViewModel(
        getPropertyUseCase = GetPropertyUseCase { result },
        savedStateHandle = SavedStateHandle(mapOf("propertyId" to propertyId)),
    )

    @Test
    fun `init loads the property on success`() = runTest {
        val vm = viewModel(result = Result.success(property(id = "1", name = "Sunny Loft")))

        val state = vm.viewState.value
        assertThat(state.property?.name).isEqualTo("Sunny Loft")
        assertThat(state.errorMessage).isNull()
    }

    @Test
    fun `init surfaces an error message on failure`() = runTest {
        val vm = viewModel(result = Result.failure(RuntimeException("not found")))

        val state = vm.viewState.value
        assertThat(state.property).isNull()
        assertThat(state.errorMessage).isEqualTo("not found")
    }

    @Test
    fun `AddAdult increments the adult count`() {
        val vm = viewModel()

        vm.onEvent(BookingEvent.AddAdult)

        assertThat(vm.viewState.value.numberOfAdults).isEqualTo(2)
    }

    @Test
    fun `SubtractAdult decrements above the floor but never drops below one`() {
        val vm = viewModel()

        vm.onEvent(BookingEvent.AddAdult) // 2
        vm.onEvent(BookingEvent.SubtractAdult) // 1
        assertThat(vm.viewState.value.numberOfAdults).isEqualTo(1)

        vm.onEvent(BookingEvent.SubtractAdult) // floored at 1
        assertThat(vm.viewState.value.numberOfAdults).isEqualTo(1)
    }

    @Test
    fun `child count increments, decrements, and floors at zero`() {
        val vm = viewModel()

        vm.onEvent(BookingEvent.SubtractChild) // floored at 0
        assertThat(vm.viewState.value.numberOfChildren).isEqualTo(0)

        vm.onEvent(BookingEvent.AddChild) // 1
        vm.onEvent(BookingEvent.AddChild) // 2
        vm.onEvent(BookingEvent.SubtractChild) // 1
        assertThat(vm.viewState.value.numberOfChildren).isEqualTo(1)
    }

    @Test
    fun `infant count increments, decrements, and floors at zero`() {
        val vm = viewModel()

        vm.onEvent(BookingEvent.SubtractInfant) // floored at 0
        assertThat(vm.viewState.value.numberOfInfants).isEqualTo(0)

        vm.onEvent(BookingEvent.AddInfant) // 1
        vm.onEvent(BookingEvent.AddInfant) // 2
        vm.onEvent(BookingEvent.SubtractInfant) // 1
        assertThat(vm.viewState.value.numberOfInfants).isEqualTo(1)
    }

    @Test
    fun `OnDateChanged updates the check-in and check-out dates`() {
        val vm = viewModel()

        vm.onEvent(BookingEvent.OnDateChanged(startDate = 1_000L, endDate = 2_000L))

        val state = vm.viewState.value
        assertThat(state.checkInDate).isEqualTo(1_000L)
        assertThat(state.checkOutDate).isEqualTo(2_000L)
    }
}