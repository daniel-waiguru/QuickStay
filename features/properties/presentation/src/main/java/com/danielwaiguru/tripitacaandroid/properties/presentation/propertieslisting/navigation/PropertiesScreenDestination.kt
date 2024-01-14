package com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertieslisting.PropertiesRoute
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.navigation.navigateToPropertyInfo
import com.danielwaiguru.tripitacaandroid.shared.navigation.AppNavigationDestination
import com.danielwaiguru.tripitacaandroid.shared.navigation.animationDuration

object PropertiesScreenDestination: AppNavigationDestination {
    override val route: String = "com.danielwaiguru.tripitacaandroid.properties.PropertiesScreen"
    override val destination: String =
        "com.danielwaiguru.tripitacaandroid.properties.PropertiesScreenDestination"
}

fun NavGraphBuilder.propertiesScreen(navController: NavHostController) {
    composable(
        route = PropertiesScreenDestination.destination,
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
        PropertiesRoute(
            onClick = {
                navController.navigateToPropertyInfo(it)
            }
        )
    }
}