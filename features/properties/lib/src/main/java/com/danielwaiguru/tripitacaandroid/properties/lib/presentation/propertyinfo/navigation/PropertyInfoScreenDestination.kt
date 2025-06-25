package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertyinfo.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.navigation.animationDuration
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertyinfo.PropertyInfoRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToPropertyInfo(propertyId: String) {
    navigate(PropertyInfoScreen(propertyId = propertyId))
}
@Serializable
internal data class PropertyInfoScreen(val propertyId: String)
fun NavGraphBuilder.propertyInfoScreen(
    navController: NavHostController,
    onClickBookNow: (id: String) -> Unit
) {
    composable<PropertyInfoScreen>(
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
        PropertyInfoRoute(
            onNavigateBack = {
                navController.popBackStack()
            },
            onClickBookNow = onClickBookNow
        )
    }
}