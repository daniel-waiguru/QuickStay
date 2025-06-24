package com.danielwaiguru.tripitacaandroid.auth.lib.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.danielwaiguru.tripitacaandroid.auth.lib.presentation.SignInRoute
import kotlinx.serialization.Serializable

@Serializable
object SignInScreen

private const val animationDuration = 700

fun NavGraphBuilder.signInScreen(onNavigateToHome: () -> Unit) {
    composable<SignInScreen>(
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
        SignInRoute(
            onNavigateToHome = onNavigateToHome
        )
    }
}