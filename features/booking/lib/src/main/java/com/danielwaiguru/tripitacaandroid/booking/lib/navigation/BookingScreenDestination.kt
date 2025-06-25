package com.danielwaiguru.tripitacaandroid.booking.lib.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielwaiguru.tripitacaandroid.booking.lib.BookingScreenRoute
import com.danielwaiguru.tripitacaandroid.shared.navigation.animationDuration
import kotlinx.serialization.Serializable

fun NavController.navigateToBookingScreen(propertyId: String) {
    navigate(BookingScreen(propertyId = propertyId))
}
@Serializable
internal data class BookingScreen(val propertyId: String)
fun NavGraphBuilder.bookingScreen(navController: NavHostController) {
    composable<BookingScreen>(
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(animationDuration)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(animationDuration)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(animationDuration)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(animationDuration)
            )
        }
    ) {
        BookingScreenRoute(
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}