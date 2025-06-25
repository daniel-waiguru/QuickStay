package com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertieslisting.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertieslisting.PropertiesRoute
import com.danielwaiguru.tripitacaandroid.properties.lib.presentation.propertyinfo.navigation.navigateToPropertyInfo
import com.danielwaiguru.tripitacaandroid.shared.navigation.animationDuration
import kotlinx.serialization.Serializable

@Serializable
object PropertiesScreen

fun NavGraphBuilder.propertiesScreen(navController: NavHostController) {
    composable<PropertiesScreen>(
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