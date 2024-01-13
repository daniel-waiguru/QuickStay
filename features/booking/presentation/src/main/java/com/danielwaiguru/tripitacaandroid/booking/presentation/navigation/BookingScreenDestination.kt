package com.danielwaiguru.tripitacaandroid.booking.presentation.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.danielwaiguru.tripitacaandroid.booking.presentation.BookingScreenRoute
import com.danielwaiguru.tripitacaandroid.shared.navigation.AppNavigationDestination

object BookingScreenDestination: AppNavigationDestination {
    override val route: String = "com.danielwaiguru.tripitacaandroid.booking.BookingScreen"
    override val destination: String =
        "com.danielwaiguru.tripitacaandroid.booking.BookingScreenDestination"
}

private const val propertyIdArg = "propertyId"
internal class PropertyArgs(val propertyId: String) {
    constructor(savedStateHandle: SavedStateHandle):
            this(checkNotNull(savedStateHandle[propertyIdArg]) as String)
}

fun NavController.navigateToBookingScreen(propertyId: String) {
    navigate("${BookingScreenDestination.route}/$propertyId")
}

fun NavGraphBuilder.bookingScreen(navController: NavHostController) {
    composable(
        route = "${BookingScreenDestination.route}/{$propertyIdArg}",
        arguments = listOf(
            navArgument(propertyIdArg) { type = NavType.StringType}
        )
    ) {
        BookingScreenRoute(
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}