package com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.danielwaiguru.tripitacaandroid.properties.presentation.propertyinfo.PropertyInfoRoute
import com.danielwaiguru.tripitacaandroid.shared.navigation.AppNavigationDestination

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
fun NavGraphBuilder.propertyInfoScreen(navController: NavHostController) {
    composable(
        route = "${PropertyInfoScreenDestination.route}/{$propertyIdArg}",
        arguments = listOf(
            navArgument(propertyIdArg) { type = NavType.StringType}
        )
    ) {
        PropertyInfoRoute(
            onNavigateBack = {
                navController.popBackStack()
            }
        )
    }
}