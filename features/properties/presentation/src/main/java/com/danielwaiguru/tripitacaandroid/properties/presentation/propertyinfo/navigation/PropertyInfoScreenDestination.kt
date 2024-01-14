package com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.PropertyInfoRoute
import com.danielwaiguru.tripitacaandroid.shared.navigation.AppNavigationDestination
import com.danielwaiguru.tripitacaandroid.shared.navigation.animationDuration

object PropertyInfoScreenDestination: AppNavigationDestination {
    override val route: String = "com.danielwaiguru.tripitacaandroid.properties.PropertyInfoScreen"
    override val destination: String =
        "com.danielwaiguru.tripitacaandroid.properties.PropertyInfoScreenDestination"
}
private const val propertyIdArg = "propertyId"
internal class PropertyArgs(val propertyId: String) {
    constructor(savedStateHandle: SavedStateHandle):
            this(checkNotNull(savedStateHandle[propertyIdArg]) as String)
}
fun NavController.navigateToPropertyInfo(propertyId: String) {
    navigate("${PropertyInfoScreenDestination.route}/$propertyId")
}
fun NavGraphBuilder.propertyInfoScreen(
    navController: NavHostController,
    onClickBookNow: (id: String) -> Unit
) {
    composable(
        route = "${PropertyInfoScreenDestination.route}/{$propertyIdArg}",
        arguments = listOf(
            navArgument(propertyIdArg) { type = NavType.StringType}
        ),
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